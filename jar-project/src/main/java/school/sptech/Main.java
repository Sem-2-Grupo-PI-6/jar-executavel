package school.sptech;

import software.amazon.awssdk.regions.Region;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        LerPersistirDados lerPersistirDados = new LerPersistirDados();
        String bucket = "s3-sixtech";
        Region region = Region.US_EAST_1;
        S3Download s3Downloader = new S3Download(bucket, region);

        List<String> arquivosXlsx = List.of(
                "inflacao.xlsx",
                "populacao.xlsx",
                "ipeaData_PIB_ConstrucaoCivil.xlsx",
                "selic.xlsx"
        );

        for (String xlsx : arquivosXlsx) {
            System.out.println("Processando arquivo: " + xlsx);

            try (FileInputStream s3InputStream = (FileInputStream) s3Downloader.baixarArquivo(xlsx)) {
                    switch (xlsx) {
                        case "inflacao.xlsx" -> lerPersistirDados.inserirDadosInflacao(s3InputStream);
                }
            } catch (IOException e) {
                System.err.println("Erro ao processar arquivo " + xlsx + ": " + e.getMessage());
            }
        }

        s3Downloader.fechar();
        System.out.println("Todos os arquivos processados com sucesso.");
    }
}
