FROM amazoncorretto:17

WORKDIR /app

COPY target/ride-matching-service.jar ride-matching-service.jar

EXPOSE 8082

CMD ["java", "-jar", "ride-matching-service.jar"]
