# ---------- Build Stage ----------
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /build

# Copy entire repo into the build stage
COPY . /build

# Find a pom.xml, run mvn against it, then copy the produced jar to /build/app.jar
RUN set -eux; \
    POM=$(find . -maxdepth 6 -name "pom.xml" | head -n 1); \
    if [ -z "$POM" ]; then echo "pom.xml not found"; exit 1; fi; \
    echo "Using POM: $POM"; \
    mvn -f "$POM" clean package -DskipTests; \
    JAR=$(find . -type f -path "*/target/*[0-9A-Za-z.-]*.jar" | head -n 1); \
    if [ -z "$JAR" ]; then echo "built jar not found"; exit 1; fi; \
    cp "$JAR" /build/app.jar

# ---------- Run Stage ----------
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /build/app.jar ./app.jar
EXPOSE 8080
CMD ["java","-jar","/app/app.jar"]
