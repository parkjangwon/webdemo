package org.parkjw.webdemo.domain.user.api;

import jakarta.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class UserAPI {

	final UserService service;

	@GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO.View> getUserById(HttpServletRequest request, @PathVariable("id") Long id) {

		log.info("{} | {} | user get api call.", request.getRemoteAddr(), id);
		UserDTO.View result = service.findUserById(id);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDTO.View>> findAll(HttpServletRequest request) {

		log.info("{} | user find all api call.", request.getRemoteAddr());
		List<UserDTO.View> result = service.findAll();

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO.View> add(HttpServletRequest request, @RequestBody UserDTO.Create dto) {

		log.info("{} | {} | user add api call.", request.getRemoteAddr(), dto.getEmail());
		User user = dto.convert(User.class);
		UserDTO.View view = service.add(user).convert(UserDTO.View.class);

		return new ResponseEntity<>(view, HttpStatus.CREATED);
	}

	@PutMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO.View> update(HttpServletRequest request, @RequestBody UserDTO.Update dto) {

		log.info("{} | {} | user update api call.", request.getRemoteAddr(), dto.getEmail());

		return new ResponseEntity<>(service.update(dto), HttpStatus.OK);
	}

	@DeleteMapping(value = "/user/{id}")
	public ResponseEntity<Boolean> deleteById(HttpServletRequest request, @PathVariable("id") Long id) {

		log.info("{} | {} | user delete api call.", request.getRemoteAddr(), id);
		return new ResponseEntity<>(service.deleteById(id), HttpStatus.OK);
	}
}
