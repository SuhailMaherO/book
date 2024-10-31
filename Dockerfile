#Use the Maven 3.8.4 image with OpenJDK 17 to build the project
FROM maven:3.8.4-openjdk-17 AS build

#Set the working directory inside the container
WORKDIR /app

#Copy the project files to the container
COPY . .

#Build the project
RUN mvn clean package -DskipTests

#Use the official OpenJDK 17 image for running the application
FROM openjdk:17-jdk-slim

#Set the working directory inside the container
WORKDIR /app

#Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

#Expose the application's port
EXPOSE 8081

#Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
