package org.parkjw.webdemo.domain.login.api;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.parkjw.webdemo.domain.lastlogin.entity.LastLogin;
import org.parkjw.webdemo.domain.lastlogin.repository.LastLoginRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class LoginAPI {

	private final LastLoginRepository lastLoginRepository;

	@GetMapping(value = "/login/{emailId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LastLogin> login(HttpServletRequest request, @PathVariable(value = "emailId") String emailId) {

		log.info("{} | {} | login api call.", request.getRemoteAddr(), emailId);
		LastLogin loginTime = new LastLogin(emailId, request.getRemoteAddr(), System.currentTimeMillis());
		lastLoginRepository.save(loginTime);

		return new ResponseEntity<>(lastLoginRepository.findById(loginTime.getEmailId()).get(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/logout/{emailId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> logout(HttpServletRequest request, @PathVariable(value = "emailId") String emailId) {

		log.info("{} | {} | logout api call.", request.getRemoteAddr(), emailId);
		lastLoginRepository.deleteById(emailId);

		return new ResponseEntity<>("logout success", HttpStatus.OK);
	}
}
