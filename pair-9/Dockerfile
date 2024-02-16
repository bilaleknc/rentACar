# Java 19 base image
FROM openjdk:19-alpine

# Set the working directory
WORKDIR /app

# Add a volume pointing to /app
VOLUME /app

# Port 8080 will be exposed for the web server
EXPOSE 8080

# Copy the JAR file into the image
COPY target/pair-9-0.0.1-SNAPSHOT.jar app.jar

# Maintainer info
LABEL maintainer="sn.bilalekinci@gmail.com"

# Run the JAR file
#ENTRYPOINT ["java","-jar","/app.jar"]
CMD ["java", "-jar", "my-java-app.jar"]
