FROM openjdk:11

USER root
WORKDIR /app

COPY . /app

COPY build/libs/warehouse-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "warehouse-0.0.1-SNAPSHOT.jar"]
