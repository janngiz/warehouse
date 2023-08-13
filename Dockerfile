FROM openjdk:11-jre-slim

WORKDIR /app

COPY build/libs/warehouse-0.0.1-SNAPSHOT.jar /app/warehouse-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "warehouse-0.0.1-SNAPSHOT.jar"]