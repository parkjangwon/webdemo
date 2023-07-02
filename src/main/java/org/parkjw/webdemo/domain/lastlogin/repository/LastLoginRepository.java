package org.parkjw.webdemo.domain.lastlogin.repository;

import org.parkjw.webdemo.domain.lastlogin.entity.LastLogin;
import org.springframework.data.repository.CrudRepository;

public interface LastLoginRepository extends CrudRepository<LastLogin, String> {

}
