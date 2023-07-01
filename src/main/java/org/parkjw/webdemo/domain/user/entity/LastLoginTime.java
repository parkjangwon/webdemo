package org.parkjw.webdemo.domain.user.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "last")
public class LastLoginTime {

	@Id
	String emailId;

	long lastLoginTime;

	public LastLoginTime(String emailId, long lastLoginTime) {
		this.emailId = emailId;
		this.lastLoginTime = lastLoginTime;
	}
}
