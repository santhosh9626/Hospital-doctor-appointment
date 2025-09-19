# ---------- Build Stage ----------
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copy only the subfolder that contains pom.xml
COPY Hospital_Appointment_Booking /app

# Build the application
RUN mvn clean package -DskipTests

# ---------- Run Stage ----------
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy only the JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Run the JAR
CMD ["java", "-jar", "app.jar"]
