# Use the OpenJDK 11 base image
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy your application's JAR file into the container
COPY build/libs/warehouse-0.0.1-SNAPSHOT.jar /app/warehouse-0.0.1-SNAPSHOT.jar

# Expose the port your application listens on
EXPOSE 8080

# Start the Java application
CMD ["java", "-jar", "warehouse-0.0.1-SNAPSHOT.jar"]
