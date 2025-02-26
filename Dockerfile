#FROM maven:3.6.0-jdk-11-slim AS build
#COPY src /home/app/src
#COPY pom.xml /home/app
#RUN mvn -f /home/app/pom.xml clean package

#FROM openjdk:11-jre-slim
#COPY --from=build /home/app/target/trans-system.jar /usr/local/lib/app.jar
#EXPOSE 10000
#ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]

FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]