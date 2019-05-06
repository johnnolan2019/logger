#FROM zenika/alpine-maven
#
#ENV MAVEN_VERSION 3.2.5
#
#RUN apt-get update && apt-get install -y \
#curl
#
#CMD /bin/bash
#
#RUN curl -sSL http://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar xzf - -C /usr/share \
#  && mv /usr/share/apache-maven-$MAVEN_VERSION /usr/share/maven \
#  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn
#
#ENV MAVEN_HOME /usr/share/maven
#
#COPY . /data/logger
#WORKDIR /data/logger
#
#RUN ["mvn", "clean", "install"]
#
#CMD ["java", "-jar", "target/logger-0.0.1-SNAPSHOT.jar"]
#
#FROM openjdk:8-jdk-alpine as build
#WORKDIR /workspace/app
#
#COPY mvnw .
#COPY .mvn .mvn
#COPY pom.xml .
#COPY src src
#
#RUN ./mvnw install -DskipTests
#RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)
#
#FROM openjdk:8-jdk-alpine
#VOLUME /tmp
#ARG DEPENDENCY=/workspace/app/target/dependency
#COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
#COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
#ENTRYPOINT ["java","-cp","app:app/lib/*","logger.Application"]

FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM java:8-jdk-alpine
COPY --from=build /home/app/target/logger-0.0.1-SNAPSHOT.jar /usr/local/lib/logger.jar
WORKDIR /usr/app
RUN sh -c 'touch logger-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","/usr/local/lib/logger.jar"]