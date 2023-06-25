package org.parkjw.webdemo.domain.login.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LoginAPI {

	@GetMapping("/api/v1/login/test")
	public void test() {
		log.info("login api test");
	}

}
