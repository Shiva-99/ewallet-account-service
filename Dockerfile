# Use a lightweight Java runtime as base image
FROM openjdk:17 as build

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged JAR file into the container
COPY target/ewallet-account-service-0.0.1-SNAPSHOT.jar /app/ewallet-account-service-0.0.1-SNAPSHOT.jar

# Expose port 8080 (default for Spring Boot applications)
EXPOSE 8080

# Start the Spring Boot application when the container starts
CMD ["java", "-jar", "/app/ewallet-account-service-0.0.1-SNAPSHOT.jar"]