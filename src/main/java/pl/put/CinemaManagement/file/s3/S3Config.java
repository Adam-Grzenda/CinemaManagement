package pl.put.CinemaManagement.file.s3;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {
    @Value("${s3.host}")
    private String host;

    @Value("${s3.port}")
    private String port;

    @Value("${s3.access_key}")
    private String accessKey;

    @Value("${s3.secret_key}")
    private String secretKey;

    @Bean
    public AmazonS3 amazonS3() {
        EndpointConfiguration endpoint = new EndpointConfiguration(host + ":" + port, "us-west-1");
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder.standard()
                .withPathStyleAccessEnabled(true)
                .withEndpointConfiguration(endpoint)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

}
