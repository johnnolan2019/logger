FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM java:8-jdk-alpine
COPY --from=build /home/app/target/logger-0.0.1-SNAPSHOT.jar /usr/local/lib/logger.jar
WORKDIR /usr/app
RUN sh -c 'touch logger-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","cmvf", "META-INF/MANIFEST.MF", "-jar","/usr/local/lib/logger.jar"]