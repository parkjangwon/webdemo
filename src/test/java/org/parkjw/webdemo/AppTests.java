package org.parkjw.webdemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.parkjw.webdemo.domain.lastlogin.entity.LastLogin;
import org.parkjw.webdemo.domain.lastlogin.repository.LastLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class AppTests {

	@Autowired
	private LastLoginRepository lastLoginRepository;

	@Test
	void contextLoads() {

		LastLogin lastLogin = new LastLogin("vim@kakao.com", "127.0.0.1", System.currentTimeMillis());

		lastLoginRepository.save(lastLogin);

		lastLoginRepository.findById(lastLogin.getEmailId());

		lastLoginRepository.count();

		lastLoginRepository.delete(lastLogin);
	}

}
