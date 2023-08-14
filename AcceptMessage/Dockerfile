FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/AcceptMessage-0.0.1-SNAPSHOT.jar /app/AcceptMessage-0.0.1-SNAPSHOT.jar

EXPOSE 8081

# Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "AcceptMessage-0.0.1-SNAPSHOT.jar"]
# java -jar .\target\AcceptMessage-0.0.1-SNAPSHOT.jar --server.port=8081
# docker build -t accept-page-app .
# docker run -d -p 8081:8080 --network my_network --name accept-page-container accept-page-app
