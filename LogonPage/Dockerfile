# Use the official OpenJDK 17 image as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY target/LogonPage-0.0.1-SNAPSHOT.jar /app/LogonPageApplication.jar

# Expose the port that the Spring Boot application is listening on
EXPOSE 8080

# Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "LogonPageApplication.jar"]

# java -jar .\target\LogonPage-0.0.1-SNAPSHOT.jar --server.port=8081

# docker network create my_network
# docker build -t logon-page-app .
# docker run -d -p 8080:8080 --network my_network --name logon-page-container logon-page-app
