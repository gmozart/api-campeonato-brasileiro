FROM openjdk:11-jdk

COPY target/*.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

CMD ["sh","-c","java -jar app.jar"]