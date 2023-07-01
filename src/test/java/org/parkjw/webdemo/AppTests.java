package org.parkjw.webdemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.parkjw.webdemo.domain.user.entity.LastLoginTime;
import org.parkjw.webdemo.domain.user.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class AppTests {

	@Autowired
	private UserLoginRepository userLoginRepository;

	@Test
	void contextLoads() {

		LastLoginTime lastLoginTime = new LastLoginTime("vim@kakao.com", System.currentTimeMillis());

		userLoginRepository.save(lastLoginTime);

		userLoginRepository.findById(lastLoginTime.getEmailId());

		userLoginRepository.count();

		userLoginRepository.delete(lastLoginTime);
	}

}
