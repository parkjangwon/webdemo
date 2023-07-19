package org.parkjw.webdemo.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;

import java.net.URI;

@Configuration
public class ObjectStorageConfig {

	@Value("${objectStorage.endpoint}")
	private String endPoint;

	@Value("${objectStorage.regionId}")
	private String regionId;

	@Value("${objectStorage.auth.accessKey}")
	private String accessKey;

	@Value("${objectStorage.auth.secretKey}")
	private String secretKey;

	@Bean
	public S3Client s3Client() {

		AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(accessKey, secretKey);

		S3Configuration s3Configuration = S3Configuration.builder().pathStyleAccessEnabled(true).build();

		return S3Client.builder().region(Region.of(regionId)).serviceConfiguration(s3Configuration).credentialsProvider(StaticCredentialsProvider.create(awsCredentials)).endpointOverride(URI.create(endPoint)).build();
	}
}
