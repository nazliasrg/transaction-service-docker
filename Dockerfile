FROM openjdk:8-jdk-alpine
COPY target/transaction-service.jar transaction-service.jar
ENTRYPOINT ["java", "-jar", "transaction-service.jar"]