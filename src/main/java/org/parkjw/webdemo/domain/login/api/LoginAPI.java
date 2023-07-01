package org.parkjw.webdemo.domain.login.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.parkjw.webdemo.domain.user.entity.LastLoginTime;
import org.parkjw.webdemo.domain.user.repository.UserLoginRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LoginAPI {

	private final UserLoginRepository userLoginRepository;

	@GetMapping("/api/v1/login/request/{emailId}")
	public ResponseEntity<LastLoginTime> login(@PathVariable(value = "emailId") String emailId) {

		log.info("login/request api test - {}", emailId);
		LastLoginTime loginTime = new LastLoginTime(emailId, System.currentTimeMillis());
		userLoginRepository.save(loginTime);

		return new ResponseEntity<>(userLoginRepository.findById(loginTime.getEmailId()).get(), HttpStatus.OK);
	}

}
