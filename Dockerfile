FROM amazoncorretto:17-alpine-jdk

#WORKDIR /app

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

#COPY target/spring-boot-docker.jar /app

ENTRYPOINT ["java", "-jar", "/app.jar"]
