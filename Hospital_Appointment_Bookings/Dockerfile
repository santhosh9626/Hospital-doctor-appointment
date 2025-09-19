# Use a lightweight JDK base image
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy Maven wrapper & source
COPY . .

# Build the application using Maven
RUN ./mvnw clean package -DskipTests

# Run the application
CMD ["java", "-jar", "target/task1-0.0.1-SNAPSHOT.jar"]
