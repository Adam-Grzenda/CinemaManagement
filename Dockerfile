FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} cinema-management.jar
ENTRYPOINT ["java", "-jar", "/cinema-management.jar"]
