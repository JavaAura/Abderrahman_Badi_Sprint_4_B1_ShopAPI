FROM openjdk:8-jdk-alpine

WORKDIR /shopapi

COPY pom.xml /shopapi/pom.xml
COPY src /shopapi/src

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

COPY target/shopapi-*.jar /shopapi/shopapi.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "/shopapi/shopapi.jar"]
