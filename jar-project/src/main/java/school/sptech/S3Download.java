package school.sptech;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

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
        try {
            ResponseInputStream<GetObjectResponse> response = s3Client.getObject(request);
            System.out.println("Download do arquivo '" + key + "' iniciado com sucesso.");
            // O getObject() retorna um ResponseInputStream, que é o objeto com o stream de dados.
            return response;

        } catch (S3Exception e) {
            // Captura erros de permissão (Access Denied) ou arquivo não encontrado (NoSuchKey)
            System.err.println("--- ERRO DE ACESSO AO S3 ---");
            System.err.println("Status: " + e.statusCode());
            System.err.println("Motivo: " + e.awsErrorDetails().errorMessage());
            System.err.println("Verifique se a IAM Role da sua EC2 possui 's3:GetObject' no bucket.");
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