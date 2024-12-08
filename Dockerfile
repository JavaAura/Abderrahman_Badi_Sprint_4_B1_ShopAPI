FROM openjdk:8-jdk-alpine

WORKDIR /shopapi

RUN ls -la

COPY pom.xml /shopapi/pom.xml
COPY mvnw /shopapi/mvnw
COPY src /shopapi/src
COPY .mvn /shopapi/.mvn

RUN chmod +x /shopapi/mvnw
RUN /shopapi/mvnw clean package -DskipTests

COPY shopapi/target/shopapi-*.jar /shopapi/shopapi.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "/shopapi/shopapi.jar"]
