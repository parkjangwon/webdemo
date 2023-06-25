package org.parkjw.webdemo.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.parkjw.webdemo.domain.user.dto.UserDTO;
import org.parkjw.webdemo.domain.user.entity.User;
import org.parkjw.webdemo.domain.user.repository.UserRepository;
import org.parkjw.webdemo.global.utils.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

	final UserRepository repository;

	public UserDTO.View findUserById(Long id) {

		return MapperUtils.map(repository.findUserById(id), UserDTO.View.class);
	}

	public List<UserDTO.View> findAll() {

		return MapperUtils.mapList(repository.findAll(), UserDTO.View.class);
	}

	public UserDTO.View add(User user) {

		return MapperUtils.map(repository.save(user), UserDTO.View.class);
	}

	public UserDTO.View update(UserDTO.Update dto) {

		User targetUser = repository.findUserById(dto.getId());

		if (dto.getName() != null) {
			targetUser.setName(dto.getName());
		}

		if (dto.getPassword() != null) {
			targetUser.setPassword(dto.getPassword());
		}

		if (dto.getEmail() != null) {
			targetUser.setEmail(dto.getEmail());
		}

		repository.save(targetUser);

		return MapperUtils.map(repository.findUserById(dto.getId()), UserDTO.View.class);
	}

	public boolean deleteById(Long id) {

		User targetUser = repository.findUserById(id);
		if (targetUser == null) {
			return false;
		} else {
			repository.deleteById(id);
			return true;
		}
	}
}
