package com.retail_syst.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RetailMangaSystemController {

	@GetMapping("/welcome")
	public String welcomeUser() {
		String text="you land in security page ";
		return text;
	}
}
