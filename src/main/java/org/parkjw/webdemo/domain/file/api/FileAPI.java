package org.parkjw.webdemo.domain.file.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.parkjw.webdemo.domain.file.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Slf4j
@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class FileAPI {

	private final FileService fileService;

	@PostMapping("/file/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile multipartFile) {

		try {
			String fileName = multipartFile.getOriginalFilename();
			if (!fileService.isFileExists(fileName)) {
				fileService.uploadFile(multipartFile.getInputStream(), fileName);
				log.info(("파일 업로드를 성공하였습니다. [{}] "), fileName);
				return ResponseEntity.ok("파일 업로드를 성공하였습니다. : " + fileName);
			} else {
				log.warn(("중복 파일입니다. [{}] "), fileName);
				return ResponseEntity.ok("중복 파일입니다. : " + fileName);
			}
		} catch (IOException e) {
			log.error("파일 업로드를 실패하였습니다.", e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to upload file", e);
		}
	}

	@GetMapping(value = "/file/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> downloadFile(@RequestParam("fileName") String fileName) {

		String prefix = "images/";

		try {
			byte[] fileBytes = fileService.downloadFile(prefix + fileName);
			return ResponseEntity.status(HttpStatus.OK).header("Content-Disposition", "attachment; filename=\"" + fileName + "\"").body(fileBytes);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to download file", e);
		}
	}
}