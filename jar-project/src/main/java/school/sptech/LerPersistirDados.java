package school.sptech;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class LerPersistirDados {

    private final Conexao conexao = new Conexao();
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(conexao.getConexao());
    private final String bucketName = "s3-sixtech";
    private final Region region = Region.US_EAST_1;
    private final S3Client s3Client;

    // Construtor
    public LerPersistirDados() {
        this.s3Client = S3Client.builder()
                .region(region)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }

    public void inserirDadosInflacao(String key) {
        try (InputStream inputStream = baixarArquivo(key);
             Workbook workbook = WorkbookFactory.create(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Date dataApuracao = row.getCell(0).getDateCellValue();
                Double taxaApuracao = row.getCell(1).getNumericCellValue();

                // Inserir no banco via JDBC
                jdbcTemplate.update(
                        "INSERT INTO inflacao (taxaInflacao, dataApuracao) VALUES (?, ?)",
                        taxaApuracao,
                        dataApuracao
                );
            }

            System.out.println("✅ Inserção de dados da inflação concluída com sucesso!");

        } catch (IOException e) {
            throw new RuntimeException("Erro de I/O ao processar o arquivo do S3 (verifique POI e formato): " + e.getMessage(), e);
        } catch (EncryptedDocumentException e) {
            throw new RuntimeException("O arquivo Excel está criptografado e não pode ser lido.", e);
        } catch (IllegalStateException e) {
            throw new RuntimeException("Erro de tipo de dado incorreto na planilha: " + e.getMessage(), e);
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro de acesso ao banco de dados durante a inserção: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado durante o processamento do Excel ou do banco: " + e.getMessage(), e);
        }
    }

    private InputStream baixarArquivo(String key) throws IOException {
        System.out.println("🔹 Baixando do S3: " + bucketName + "/" + key);

        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        try {
            ResponseInputStream<GetObjectResponse> response = s3Client.getObject(request);
            System.out.println("✅ Arquivo carregado do S3 com sucesso!");
            return response;
        } catch (S3Exception e) {
            System.err.println("❌ Erro ao baixar do S3: " + e.awsErrorDetails().errorMessage());
            throw new IOException("Falha ao obter InputStream do S3", e);
        }
    }

    public void fecharS3() {
        s3Client.close();
        System.out.println("🔒 Cliente S3 fechado.");
    }
}
