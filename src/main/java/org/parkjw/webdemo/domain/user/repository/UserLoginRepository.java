package org.parkjw.webdemo.domain.user.repository;

import org.parkjw.webdemo.domain.user.entity.LastLoginTime;
import org.springframework.data.repository.CrudRepository;

public interface UserLoginRepository extends CrudRepository<LastLoginTime, String> {

}
