package org.parkjw.webdemo.domain.file.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

	@GetMapping("/")
	public String index() {

		return "global/file";
	}

}