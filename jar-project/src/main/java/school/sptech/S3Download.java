package school.sptech;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

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

    public InputStream baixarArquivo(String key) {
        System.out.println("Baixando arquivo do S3: " + key);

        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        ResponseInputStream<GetObjectResponse> response = s3Client.getObject(request);
        System.out.println("Download concluído.");
        return response;
    }

    public void fechar() throws IOException {
        s3Client.close();
    }
}
