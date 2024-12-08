FROM openjdk:8-jdk-alpine

WORKDIR /shopapi

# COPY shopapi/pom.xml /shopapi/pom.xml
# COPY shopapi/mvnw /shopapi/mvnw
# COPY shopapi/src /shopapi/src
# COPY shopapi/.mvn /shopapi/.mvn

# RUN chmod +x /shopapi/mvnw
# RUN /shopapi/mvnw clean package -DskipTests

COPY shopapi/target/shopapi-*.jar /shopapi/shopapi.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "/shopapi/shopapi.jar"]
