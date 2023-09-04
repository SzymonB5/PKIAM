package com.example.LogonPage;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.net.ServerSocket;
import java.net.Socket;

@SpringBootApplication
@RestController
public class LogonPageApplication {
	private static final String PROCESS_STRING_URL = "http://appacc:8080/processString";
	private static final String PROCESS_LOGIN_URL = "http://appacc:8080/logger";
	private static final String DATABASE_CONNECTOR = "http://databasecontroller:5000";
	private final static String QUEUE_NAME = "hello";

	public static void main(String[] args) {
		SpringApplication.run(LogonPageApplication.class, args);
	}

	@GetMapping("/test")
	public String testtw() {
		return "Logon-Page1213Reload";
	}

	@GetMapping("/testrmq")
	public String testt() {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("rabbitmq"); // RabbitMQ server hostname
		String message = "";

		try {
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			message = "Hello, RabbitMQ!";
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		}
		catch (Exception e) {
			return e.getMessage();
		}
		finally {
			System.out.println(" [x] Sent '" + message + "'");
		}

		return message;
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

	@GetMapping("/login")
	public String login() {
		StringBuilder stringBuilder = new StringBuilder("<html>");

		stringBuilder.append("<body>");

		stringBuilder.append("<label> FirstName: </label><br/>");
		stringBuilder.append("<input type=\"text\" id=\"tbfirstname\"/><br/>");

		stringBuilder.append("<label> Password </label><br/>");
		stringBuilder.append("<input type=\"text\" id=\"tbPassword\"/><br/>");

		stringBuilder.append("<button type=\"button\" onclick=\"myFunction()\">Submit</button><br/>");

		stringBuilder.append("<script>");
		stringBuilder.append("function myFunction(){");
		stringBuilder.append("    var firstName = document.getElementById('tbfirstname').value;");
		stringBuilder.append("    var lastName = '.';");
		stringBuilder.append("    var password = document.getElementById('tbPassword').value;");
		stringBuilder.append("    var data = { FirstName : firstName, Pass : password, LastName : lastName };");
		stringBuilder.append("    fetch('/vallogin', {");
		stringBuilder.append("        method: 'POST',");
		stringBuilder.append("        headers: {");
		stringBuilder.append("            'Content-Type': 'application/json'");
		stringBuilder.append("        },");
		stringBuilder.append("        body: JSON.stringify(data)");
		stringBuilder.append("    })");
		stringBuilder.append("    .then(response => response.text())");
		stringBuilder.append("    .then(data => {");
		stringBuilder.append("        if (data === 'True') {");
		stringBuilder.append("            alert('Login successful!');");
		stringBuilder.append("        } else {");
		stringBuilder.append("            alert('Login failed. Please check your credentials.');");
		stringBuilder.append("        }");
		stringBuilder.append("    });");
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
		stringBuilder.append("    var fn = document.getElementById('tbFirstName').value;");
		stringBuilder.append("    var ln = document.getElementById('tbLastName').value;");
		stringBuilder.append("    var pass = document.getElementById('tbPassword').value;\n");
		stringBuilder.append("    var payload = {");
		stringBuilder.append("        FirstName : fn,");
		stringBuilder.append("        LastName : ln,");
		stringBuilder.append("        Pass : pass");
		stringBuilder.append("    };");
		stringBuilder.append("    console.log(payload);");
		stringBuilder.append("    console.log(fn);");
		stringBuilder.append("    console.log(ln);");
		stringBuilder.append("    console.log(pass);");
		stringBuilder.append("    fetch('/isfraud', {");
		stringBuilder.append("        method: 'POST',");
		stringBuilder.append("        headers: {");
		stringBuilder.append("            'Content-Type': 'application/json'");
		stringBuilder.append("        },");
		stringBuilder.append("        body: JSON.stringify(payload)");
		stringBuilder.append("    })");
		stringBuilder.append("    .then(response => response.text())");
		stringBuilder.append("    .then(data => {");
		stringBuilder.append("        console.log(data);");
		stringBuilder.append("        if (data === 'True') {");
		stringBuilder.append("            alert('User registered successfully.');");
		stringBuilder.append("        } else if (data === 'False') {");
		stringBuilder.append("            alert('Failed to add user.');");
		stringBuilder.append("        } else {");
		stringBuilder.append("            alert('Error.');");
		stringBuilder.append("        }");
		stringBuilder.append("    })");
		stringBuilder.append("    .catch(error => {");
		stringBuilder.append("        console.error('Error:', error);");
		stringBuilder.append("        alert('An error occurred while processing the request.');");
		stringBuilder.append("    });");
		stringBuilder.append("}");
		stringBuilder.append("</script>");


		stringBuilder.append("</body>");
		stringBuilder.append("</html>");

		return stringBuilder.toString();
	}

	@PostMapping("/isfraud")
	public String validate(@RequestBody Fraud fraud) {

		String responseBody = "OK";
		try {
			RestTemplate restTemplate = new RestTemplate();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			ObjectMapper objectMapper = new ObjectMapper();
			String requestBody = objectMapper.writeValueAsString(fraud);

			HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);

			ResponseEntity<String> response = restTemplate.exchange(DATABASE_CONNECTOR + "/validate", HttpMethod.POST, httpEntity, String.class);

			responseBody = response.getBody();
		} catch (Exception e) {
			return e.getMessage();
		}
		
		return responseBody;
//		return "Main Page\n" + "Response from /processString: " + responseBody + " " + fraud.LastName() + fraud.FirstName() + fraud.Pass() + "From Python" + responseBody + "<End>";
	}

	@PostMapping("/vallogin")
	public String vallogin(@RequestBody Fraud fraud) {
		String responseBody = "";
		try {
			RestTemplate restTemplate = new RestTemplate();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			String requestBody = fraud.FirstName() + "." + fraud.Pass();
			HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);

			ResponseEntity<String> response = restTemplate.exchange(PROCESS_LOGIN_URL, HttpMethod.POST, httpEntity, String.class);

			responseBody = response.getBody();
		} catch (Exception e) {
			return e.getMessage();
		}

		return responseBody;
	}

	@PostMapping("/processString")
	public String processString(@RequestBody String inputString) {
		return "??: " + inputString;
	}
}
