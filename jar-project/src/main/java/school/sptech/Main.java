package school.sptech;

import software.amazon.awssdk.regions.Region;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
            LerPersistirDados lerPersistirDados = new LerPersistirDados();
            String bucket = "s3-sixtech";
            Region region = Region.US_EAST_1;
            S3Download s3Downloader = new S3Download(bucket, region);
            
            List<String> arquivosXlsx = new ArrayList<>();
            arquivosXlsx.add("inflacao.xlsx");
            arquivosXlsx.add("populacao.xlsx");
            arquivosXlsx.add("ipeaData_PIB_ConstrucaoCivil.xlsx.xlsx");
            arquivosXlsx.add("selic.xlsx");

        for (String xlsx : arquivosXlsx) {
            if(xlsx.equals("inflacao.xlsx")){
                InputStream inputStream = s3Downloader.baixarArquivo(xlsx);
                lerPersistirDados.inserirDadosInflacao(inputStream);
            }
        }

    }
}
