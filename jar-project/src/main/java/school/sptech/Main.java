package school.sptech;

import software.amazon.awssdk.regions.Region;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String bucket = "s3-sixtech";
        String pastaLocal = "/home/ubuntu/baseDeDados/";
        Region region = Region.US_EAST_1;

        S3Download s3Downloader = new S3Download(bucket, region);
        LerPersistirDados lerPersistirDados = new LerPersistirDados();

        // ✅ Lista dos arquivos a serem processados
        List<String> arquivosXlsx = List.of(
                "inflacao.xlsx",
                "populacao.xlsx",
                "ipeaData_PIB_ConstrucaoCivil.xlsx",
                "selic.xlsx"
        );

        try {
            for (String arquivo : arquivosXlsx) {
                String caminhoLocal = pastaLocal + arquivo;

                s3Downloader.baixarArquivoParaDisco(arquivo, caminhoLocal);

                // 2️⃣ Ler com Apache POI e inserir no banco
                try (FileInputStream fis = new FileInputStream(caminhoLocal)) {

                    switch (arquivo) {
                        case "inflacao.xlsx" -> lerPersistirDados.inserirDadosInflacao(fis);
                        default -> System.out.println("⚠️ Nenhum método de leitura definido para " + arquivo);
                    }

                } catch (IOException e) {
                    System.err.println("❌ Erro ao ler o arquivo local: " + caminhoLocal);
                    e.printStackTrace();
                }

                System.out.println("✅ Arquivo " + arquivo + " processado com sucesso.");
            }

            System.out.println("\n🎯 Todos os arquivos foram processados com sucesso!");

        } catch (Exception e) {
            System.err.println("💥 Erro inesperado no processamento geral: " + e.getMessage());
            e.printStackTrace();
        } finally {
            s3Downloader.fechar();
        }
    }
}
