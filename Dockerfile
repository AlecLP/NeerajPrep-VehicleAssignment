# ---------- Stage 1: Build the JAR ----------
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean install -DskipTests

# ---------- Stage 2: Run the Application ----------
FROM openjdk:17-jdk

WORKDIR /app

COPY --from=build /app/target/NeerajPrep-Vehicle-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]