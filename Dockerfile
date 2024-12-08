FROM openjdk:8-jdk-alpine

WORKDIR /shopapi

COPY pom.xml /shopapi/pom.xml
COPY mvnw /shopapi/mvnw
COPY src /shopapi/src
COPY .mvn /shopapi/.mvn

RUN chmod +x /shopapi/mvnw
RUN ./mvnw clean package -DskipTests

COPY target/shopapi-*.jar /shopapi/shopapi.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "/shopapi/shopapi.jar"]
