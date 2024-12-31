# Use an official Gradle image to build the application
FROM gradle:8.11.1-jdk21 AS build
WORKDIR /app

# Copy the project files
COPY . /app

# Grant execution permissions for gradlew
RUN chmod +x ./gradlew

# Build the application
RUN ./gradlew build --warning-mode all

# Use an official OpenJDK runtime image
FROM openjdk:21-jdk-slim

# Copy the built application
COPY --from=build /app/build/libs/*.jar /app/app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
