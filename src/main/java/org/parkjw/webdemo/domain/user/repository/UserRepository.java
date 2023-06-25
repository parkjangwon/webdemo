package org.parkjw.webdemo.domain.user.repository;

import org.parkjw.webdemo.domain.user.dto.UserDTO;
import org.parkjw.webdemo.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

	User findUserById(Long id);
	List<User> findAll();

	User save(User user);

	void deleteById(Long id);
}
