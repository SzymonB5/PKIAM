package com.example.LogonPage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;


@SpringBootApplication
@RestController
public class LogonPageApplication {
	private static final String PROCESS_STRING_URL = "http://appacc:8080/processString";
	private static final String DATABASE_CONNECTOR = "http://databaseconnector:8080/processString";

	public static void main(String[] args) {
		SpringApplication.run(LogonPageApplication.class, args);
	}

	@GetMapping("/test")
	public String testt() {
		return "Logon-Page1213Reload";
	}

	@GetMapping("/send")
	public String test() {

		String responseBody = "";
		try {
			RestTemplate restTemplate = new RestTemplate();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			String requestBody = "{testttgfvrdf}";
			HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);

			ResponseEntity<String> response = restTemplate.exchange(PROCESS_STRING_URL, HttpMethod.POST, httpEntity, String.class);

			responseBody = response.getBody();
		} catch (Exception e) {
			return e.getMessage();
		}

		return "Main Page\n" + "Response from /processString: " + responseBody;
	}

	@GetMapping("/testdb")
	public String testdb() {

		String responseBody = "";
		try {
			RestTemplate restTemplate = new RestTemplate();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			String requestBody = "{testttgfvrdf}";
			HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);

			ResponseEntity<String> response = restTemplate.exchange(DATABASE_CONNECTOR, HttpMethod.GET, httpEntity, String.class);

			responseBody = response.getBody();
		} catch (Exception e) {
			return e.getMessage();
		}

		return "DB " + responseBody;
	}

	@GetMapping("/login")
	public String login() {
		StringBuilder stringBuilder = new StringBuilder("<html>");

		stringBuilder.append("<body>");

		stringBuilder.append("<label> Email: </label><br/>");
		stringBuilder.append("<input type=\"text\" id=\"email\"/><br/>");

		stringBuilder.append("<label> Password </label><br/>");
		stringBuilder.append("<input type=\"text\" id=\"tbPassword\"/><br/>");

		stringBuilder.append("<button type=\"button\" onclick=\"myFunction()\">Submit</button><br/>");

		stringBuilder.append("<script>");
		stringBuilder.append("function myFunction() {");
		stringBuilder.append("    alert(\"Button clicked!\");");
		stringBuilder.append("}");
		stringBuilder.append("</script>");

		stringBuilder.append("</body>");

		stringBuilder.append("</html>");

		return stringBuilder.toString();
	}

	@GetMapping("/register")
	public String register() {
		StringBuilder stringBuilder = new StringBuilder("<html>");

		stringBuilder.append("<body>");

		stringBuilder.append("<label> FirstName </label><br/>");
		stringBuilder.append("<input type=\"text\" id=\"tbFirstName\"/><br/>");

		stringBuilder.append("<label> LastName </label><br/>");
		stringBuilder.append("<input type=\"text\" id=\"tbLastName\"/><br/>");

		stringBuilder.append("<label> Password </label><br/>");
		stringBuilder.append("<input type=\"text\" id=\"tbPassword\"/><br/>");

		stringBuilder.append("<label> PasswordConf </label><br/>");
		stringBuilder.append("<input type=\"text\" id=\"PasswordConf\"/><br/>");

		stringBuilder.append("<button type=\"button\" onclick=\"myFunction()\">Submit</button><br/>");

		stringBuilder.append("<script>");
		stringBuilder.append("function myFunction() {");
		stringBuilder.append("    alert(\"Button clicked!!\");");
		stringBuilder.append("}");
		stringBuilder.append("</script>");

		stringBuilder.append("</body>");
		stringBuilder.append("</html>");

		return stringBuilder.toString();
	}

	@PostMapping("/processString")
	public String processString(@RequestBody String inputString) {
		return "WTF: " + inputString;
	}
}
