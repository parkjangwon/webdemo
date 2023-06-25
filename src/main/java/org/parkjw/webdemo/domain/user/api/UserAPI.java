package org.parkjw.webdemo.domain.user.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.parkjw.webdemo.domain.user.dto.UserDTO;
import org.parkjw.webdemo.domain.user.entity.User;
import org.parkjw.webdemo.domain.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserAPI {

	final UserService service;

	@GetMapping(value = "/api/v1/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO.View> getUserById(@PathVariable("id") Long id) {

		UserDTO.View result = service.findUserById(id);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/api/v1/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDTO.View>> findAll() {

		List<UserDTO.View> result = service.findAll();

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping(value = "/api/v1/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO.View> add(@RequestBody UserDTO.Create dto) {

		User user = dto.convert(User.class);
		UserDTO.View view = service.add(user).convert(UserDTO.View.class);

		return new ResponseEntity<>(view, HttpStatus.CREATED);
	}

	@PutMapping(value = "/api/v1/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO.View> update(@RequestBody UserDTO.Update dto) {
		return new ResponseEntity<>(service.update(dto), HttpStatus.OK);
	}

	@DeleteMapping(value = "/api/v1/user/{id}")
	public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id) {

		return new ResponseEntity<>(service.deleteById(id), HttpStatus.OK);
	}
}
