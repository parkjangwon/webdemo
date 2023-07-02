package org.parkjw.webdemo.domain.user.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "last")
public class LastLoginTime {

	@Id
	String emailId;

	String ip;

	long loginTime;

	public LastLoginTime(String emailId, String ip, long loginTime) {
		this.emailId = emailId;
		this.ip = ip;
		this.loginTime = loginTime;
	}
}
