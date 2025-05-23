# Stage 1: Build the application using Maven
FROM maven:3.9.6-eclipse-temurin-21 as builder

# Set working directory in container
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the rest of the application source code
COPY src ./src

# Package the application (skip tests for faster build)
RUN mvn clean package -DskipTests

# Stage 2: Create a minimal runtime image
FROM eclipse-temurin:21-jre

# Set working directory
WORKDIR /app

# Copy the jar from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the port your app runs on
EXPOSE 8081

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]


#repository name: 971219201755.dkr.ecr.us-east-1.amazonaws.com/
#AWS ECR login command
#aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 971219201755.dkr.ecr.us-east-1.amazonaws.com

#http://44.205.246.220:8081/suprime-api