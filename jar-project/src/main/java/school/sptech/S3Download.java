package school.sptech;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class S3Download {

    private final S3Client s3Client;
    private final String bucketName;

    public S3Download(String bucketName, Region region) {
        this.bucketName = bucketName;
        this.s3Client = S3Client.builder()
                .region(region)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }

    public InputStream baixarArquivo(String key) throws IOException {
        System.out.println("Tentando baixar o arquivo do S3: " + bucketName + "/" + key);

        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        try (ResponseInputStream<GetObjectResponse> response = s3Client.getObject(request)) {
            byte[] bytes = response.readAllBytes();
            System.out.println("Download do arquivo '" + key + "' concluído com sucesso. Tamanho: " + bytes.length + " bytes");

            return new ByteArrayInputStream(bytes);
        } catch (S3Exception e) {
            System.err.println("Status: " + e.statusCode());
            System.err.println("Motivo: " + e.awsErrorDetails().errorMessage());
            throw new IOException("Falha no S3 ao baixar: " + e.awsErrorDetails().errorMessage(), e);
        }
    }

    public void fechar() {
        if (s3Client != null) {
            s3Client.close();
            System.out.println("Cliente S3 fechado.");
        }
    }
}
