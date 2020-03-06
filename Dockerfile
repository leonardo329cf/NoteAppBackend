FROM openjdk:11
ADD target/backend-noteapp.jar backend-noteapp.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "backend-noteapp.jar"]