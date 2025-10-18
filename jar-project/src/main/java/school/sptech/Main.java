package school.sptech;

import software.amazon.awssdk.regions.Region;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String bucket = "s3-sixtech";
        Region region = Region.US_EAST_1;

        S3Download s3Downloader = new S3Download(bucket, region);
        LerPersistirDados lerPersistirDados = new LerPersistirDados();

        // Lista de arquivos que você quer processar
        List<String> arquivosXlsx = Arrays.asList(
                "inflacao.xlsx",
                "populacao.xlsx",
                "ipeaData_PIB_ConstrucaoCivil.xlsx",
                "selic.xlsx"
        );

        try {
            for (String arquivo : arquivosXlsx) {
                System.out.println("Processando arquivo: " + arquivo);

                try (InputStream inputStream = s3Downloader.baixarArquivo(arquivo)) {
                    switch (arquivo) {
                        case "inflacao.xlsx":
                            lerPersistirDados.inserirDadosInflacao(inputStream);
                            break;
                        /*
                        case "populacao.xlsx":
                            lerPersistirDados.inserirDadosPopulacao(inputStream);
                            break;
                        case "ipeaData_PIB_ConstrucaoCivil.xlsx":
                            lerPersistirDados.inserirDadosipeaData_PIB_ConstrucaoCivil(inputStream);
                            break;
                        case "selic.xlsx":
                            lerPersistirDados.inserirDadosSelic(inputStream);
                            break;
                        */
                        default:
                            System.out.println("Arquivo não mapeado para inserção: " + arquivo);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao baixar/processar arquivos do S3: " + e.getMessage());
            e.printStackTrace();
        } finally {
            s3Downloader.fechar();
        }
    }
}
