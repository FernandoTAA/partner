FROM openjdk:11.0.10-jdk-slim-buster

MAINTAINER Fernando Teixeira Alves de Araujo

RUN apt-get -y update && \
    apt-get -y autoremove && \
    apt-get -y autoclean && \
    apt-get -y install wget && \
    apt-get -y install unzip && \
    apt-get -y clean

RUN wget "https://services.gradle.org/distributions/gradle-6.5.1-bin.zip" -O gradle-6.5.1-bin.zip && \
    unzip -d /opt/gradle gradle-6.5.1-bin.zip
ENV PATH=$PATH:/opt/gradle/gradle-6.5.1/bin

COPY . /app/
WORKDIR /app/

EXPOSE 8080

RUN gradle build

ARG SPRING_PROFILES_ACTIVE
ENV SPRING_PROFILES_ACTIVE $SPRING_PROFILES_ACTIVE

CMD java -jar /app/build/libs/partner-0.0.1-SNAPSHOT.jar
