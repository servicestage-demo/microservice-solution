FROM openjdk:8u181-jdk-alpine

WORKDIR /home/apps/

COPY target/lib lib

COPY target/*.jar app.jar

RUN sh -c 'touch app.jar'

ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar -Xmx128m app.jar" ]
