package pl.put.CinemaManagement.file.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class S3FileService implements FileService {

    private final AmazonS3 amazonS3;

    @Value("${s3.bucket}")
    private String bucket;

    @Override
    public byte[] get(String key) throws IOException {
        return amazonS3.getObject(new GetObjectRequest(bucket, key)).getObjectContent().readAllBytes();
    }

    @Override
    public void put(String key, InputStream fileInputStream) throws IOException {
        File tempFile = Files.createTempFile(UUID.randomUUID().toString(), "").toFile();
        try {
            FileUtils.copyInputStreamToFile(fileInputStream, tempFile);
            amazonS3.putObject(new PutObjectRequest(bucket, key, tempFile));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (tempFile.exists()) {
                tempFile.delete();
            }
        }
    }
}
