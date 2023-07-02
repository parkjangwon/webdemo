package org.parkjw.webdemo.domain.lastlogin.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "last")
public class LastLogin {

	@Id
	String emailId;

	String ip;

	long loginTime;

	public LastLogin(String emailId, String ip, long loginTime) {
		this.emailId = emailId;
		this.ip = ip;
		this.loginTime = loginTime;
	}
}
