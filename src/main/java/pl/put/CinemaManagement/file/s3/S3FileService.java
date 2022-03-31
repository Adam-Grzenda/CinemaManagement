package pl.put.CinemaManagement.file.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.put.CinemaManagement.file.FileDetails;
import pl.put.CinemaManagement.file.FileListRequest;
import pl.put.CinemaManagement.file.FileService;
import pl.put.CinemaManagement.file.FileServiceException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class S3FileService implements FileService {

    private final AmazonS3 amazonS3;

    @Value("${s3.bucket}")
    private String bucket;

    @Override
    public byte[] get(String key) {
        try {
            return amazonS3.getObject(new GetObjectRequest(bucket, key)).getObjectContent().readAllBytes();
        } catch (IOException e) {
            throw new FileServiceException(e.getMessage());
        }
    }

    @Override
    public void put(String key, InputStream fileInputStream) {
        File tempFile = null;
        try {
            tempFile = Files.createTempFile(UUID.randomUUID().toString(), "").toFile();
            FileUtils.copyInputStreamToFile(fileInputStream, tempFile);
            amazonS3.putObject(new PutObjectRequest(bucket, key, tempFile));
        } catch (IOException e) {
            throw new FileServiceException(e.getMessage());
        } finally {
            if (tempFile != null && tempFile.exists()) {
                tempFile.delete();
            }
        }
    }

    @Override
    public void delete(String key) {
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, key));
    }

    @Override
    public S3FileList listFiles(FileListRequest request) {
        var listRequest = new ListObjectsV2Request()
                .withBucketName(bucket)
                .withMaxKeys(request.maxKeys());

        if (request.continuationToken() != null) {
            listRequest = listRequest.withContinuationToken(request.continuationToken());
        }

        var listObjectResponse = amazonS3.listObjectsV2(listRequest);

        List<FileDetails> fileDetails = listObjectResponse.getObjectSummaries()
                .stream()
                .map(summary -> new FileDetails(summary.getKey()))
                .toList();

        return S3FileList.builder()
                .files(fileDetails)
                .isTruncated(listObjectResponse.isTruncated())
                .continuationToken(listObjectResponse.getContinuationToken())
                .build();
    }


}
