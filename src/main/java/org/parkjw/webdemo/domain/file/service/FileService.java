package org.parkjw.webdemo.domain.file.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class FileService {

	private final S3Client s3Client;

	@Value("${objectStorage.bucketName}")
	private String bucketName;

	public void uploadFile(InputStream inputStream, String key) {

		byte[] fileBytes;
		try {
			fileBytes = inputStream.readAllBytes();
		} catch (IOException e) {
			throw new RuntimeException("Failed to read file contents", e);
		}

		PutObjectRequest request = PutObjectRequest.builder().bucket(bucketName).key(key).build();

		s3Client.putObject(request, RequestBody.fromBytes(fileBytes));
	}

	public byte[] downloadFile(String key) throws IOException {

		GetObjectRequest request = GetObjectRequest.builder().bucket(bucketName).key(key).build();

		return s3Client.getObject(request).readAllBytes();
	}

	public boolean isFileExists(String key) {

		GetObjectRequest request = GetObjectRequest.builder().bucket(bucketName).key(key).build();

		try {
			s3Client.getObject(request);
			return true; // 파일이 존재하는 경우
		} catch (NoSuchKeyException e) {
			return false; // 파일이 존재하지 않는 경우
		}
	}
}
