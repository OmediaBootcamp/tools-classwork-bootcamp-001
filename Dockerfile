FROM openjdk:11.0.15-oracle

WORKDIR /app

COPY /target/*.jar app.jar
COPY file.yaml file.yaml

CMD ["java", "-jar", "app.jar"]