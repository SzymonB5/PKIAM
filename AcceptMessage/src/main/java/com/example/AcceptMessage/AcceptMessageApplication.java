package com.example.AcceptMessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AcceptMessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcceptMessageApplication.class, args);
	}

	@GetMapping("/test")
	public String testt() {
		return "Accept-message-Page";
	}

	@PostMapping("/processString")
	public String processString(@RequestBody String inputString) {
		return "Processed string: " + inputString;
	}

}
