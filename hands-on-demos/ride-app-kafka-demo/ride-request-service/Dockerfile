FROM amazoncorretto:17

# Set working directory
WORKDIR /app

# Copy build files
COPY target/ride-request-service.jar ride-request-service.jar

# Expose application port
EXPOSE 8081

# Run the application
CMD ["java", "-jar", "ride-request-service.jar"]