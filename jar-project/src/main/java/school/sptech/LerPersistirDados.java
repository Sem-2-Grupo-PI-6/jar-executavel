package school.sptech;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class LerPersistirDados {
    Conexao conexao = new Conexao();
    JdbcTemplate jdbcTemplate = new JdbcTemplate(conexao.getConexao());

    public void inserirDadosInflacao(InputStream arquivo) {
        try (Workbook workbook = WorkbookFactory.create(arquivo)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                Date dataApuracao = row.getCell(0).getDateCellValue();
                Double taxaApuracao = row.getCell(1).getNumericCellValue();

                Inflacao inflacao = new Inflacao(dataApuracao, taxaApuracao);
                inflacao.setTaxaInflacao(taxaApuracao);
                inflacao.setDataApuracao(dataApuracao);

                jdbcTemplate.update(
                        "INSERT INTO inflacao (, inflacao) VALUES (?, ?)",
                        inflacao.getTaxaInflacao(),
                        inflacao.getDataApuracao()
                );
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro de I/O ao processar o arquivo (Verifique POI e formato): " + e.getMessage(), e);
        } catch (EncryptedDocumentException e) {
            throw new RuntimeException("O arquivo Excel está criptografado e não pode ser lido.", e);
        } catch (IllegalStateException e) {
            throw new RuntimeException("Erro de estado na célula (Tipo de dado incorreto na coluna): " + e.getMessage(), e);
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro de acesso ao banco de dados durante a inserção: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado durante o processamento do Excel ou DB: " + e.getMessage(), e);
        }
    }

    /*public void inserirDadosSelic(InputStream inputStream) {
        try (Workbook workbook = WorkbookFactory.create(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                Date dataApuracao = row.getCell(0).getDateCellValue();
                Double taxaApurcao = row.getCell(1).getNumericCellValue();

                Inflacao inflacao = new Inflacao(dataApuracao, taxaApurcao);
                inflacao.setDataApuracao(String.valueOf(dataApuracao));
                inflacao.setTaxaInflacao(taxaApurcao);

                jdbcTemplate.update(
                        "INSERT INTO inflacao (taxaInflacao, dataApuracao) VALUES (?, ?)",
                        inflacao.getMunicipio(),
                        inflacao.getInflacao()
                );
            }

        } catch (IOException e) {
            throw new RuntimeException("Erro ao processar arquivo Excel", e);
        }
    }

    public void inserirDadosPopulacao(InputStream inputStream) {
        try (Workbook workbook = WorkbookFactory.create(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                String mun = row.getCell(0).getStringCellValue();
                Double num = row.getCell(1).getNumericCellValue();

                Inflacao inflacao = new Inflacao();
                inflacao.setMunicipio(mun);
                inflacao.setInflacao(num);

                jdbcTemplate.update(
                        "INSERT INTO inflacao (municipio, inflacao) VALUES (?, ?)",
                        inflacao.getMunicipio(),
                        inflacao.getInflacao()
                );
            }

        } catch (IOException e) {
            throw new RuntimeException("Erro ao processar arquivo Excel", e);
        }
    }

    public void inserirDadosipeaData_PIB_ConstrucaoCivil(InputStream inputStream) {
        try (Workbook workbook = WorkbookFactory.create(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                String mun = row.getCell(0).getStringCellValue();
                Double num = row.getCell(1).getNumericCellValue();

                Inflacao inflacao = new Inflacao();
                inflacao.setMunicipio(mun);
                inflacao.setInflacao(num);

                jdbcTemplate.update(
                        "INSERT INTO inflacao (municipio, inflacao) VALUES (?, ?)",
                        inflacao.getMunicipio(),
                        inflacao.getInflacao()
                );
            }

        } catch (IOException e) {
            throw new RuntimeException("Erro ao processar arquivo Excel", e);
        }
    }*/
}
