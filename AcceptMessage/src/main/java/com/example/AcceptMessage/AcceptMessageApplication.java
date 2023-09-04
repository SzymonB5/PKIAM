package com.example.AcceptMessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

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

	@PostMapping("/logger")
	public String dbconn(@RequestBody String fraud) {
		String[] parts = fraud.split("\\.");
		String fn = parts[0];
		String pass = parts[1];
		String url = "jdbc:postgresql://postgres:5432/dbname";
		String user = "amigoscode";
		String password = "password";

		String response = "False";
		try {
			Class.forName("org.postgresql.Driver");

			Connection connection = DriverManager.getConnection(url, user, password);

			String selectSQL = "SELECT * FROM registered WHERE firstname = ? AND password = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, fn);
			preparedStatement.setString(2, pass);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				response = "True";
			}

			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			response = e.toString();
		}

        return response;
	}



	@PostMapping("/processString")
	public String processString(@RequestBody String inputString) {
		return "Processed string: " + inputString;
	}

}
