FROM openjdk:18-ea-jdk-slim
VOLUME /tmp
COPY target/my-gateway-0.0.1-SNAPSHOT.jar api-gateway-service.jar
ENTRYPOINT ["java","-jar","api-gateway-service.jar"]