package school.sptech;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.FileOutputStream;
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

    public void baixarArquivoParaDisco(String key, String caminhoLocal) throws IOException {
        System.out.println("⬇️ Baixando do S3: " + bucketName + "/" + key);
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        try (ResponseInputStream<GetObjectResponse> response = s3Client.getObject(request);
             FileOutputStream fos = new FileOutputStream(caminhoLocal)) {

            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = response.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }

            System.out.println("✅ Arquivo salvo em: " + caminhoLocal);
        } catch (S3Exception e) {
            System.err.println("Erro ao baixar do S3: " + e.awsErrorDetails().errorMessage());
            throw new IOException("Falha no download do S3", e);
        }
    }

    public void fechar() {
        s3Client.close();
        System.out.println("🔒 Cliente S3 fechado.");
    }
}
