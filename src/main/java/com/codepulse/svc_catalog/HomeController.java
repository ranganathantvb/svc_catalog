package com.codepulse.svc_catalog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "Catalog service is running.";
	}

	@GetMapping("/health")
	public String health() {
		return "{\"status\":\"UP\"}";
	}
}
