package org.parkjw.webdemo.domain.login.api;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.parkjw.webdemo.domain.user.entity.LastLoginTime;
import org.parkjw.webdemo.domain.user.repository.UserLoginRepository;
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

	private final UserLoginRepository userLoginRepository;

	@GetMapping(value = "/login/{emailId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LastLoginTime> login(HttpServletRequest request, @PathVariable(value = "emailId") String emailId) {

		log.info("{} | {} | login api call.", request.getRemoteAddr(), emailId);
		LastLoginTime loginTime = new LastLoginTime(emailId, request.getRemoteAddr(), System.currentTimeMillis());
		userLoginRepository.save(loginTime);

		return new ResponseEntity<>(userLoginRepository.findById(loginTime.getEmailId()).get(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/logout/{emailId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> logout(HttpServletRequest request, @PathVariable(value = "emailId") String emailId) {

		log.info("{} | {} | logout api call.", request.getRemoteAddr(), emailId);
		userLoginRepository.deleteById(emailId);

		return new ResponseEntity<>("logout success", HttpStatus.OK);
	}
}
