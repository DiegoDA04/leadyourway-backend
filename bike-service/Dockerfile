# The base image on which we would build our image
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the pom.xml file to the working directory
COPY target/bike-service-0.0.1-SNAPSHOT.jar ./app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]