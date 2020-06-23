FROM openjdk:14

ENV ENVIRONMENT=prod

MAINTAINER Thanh Duong <thanh.duong2503@hotmail.de>

ADD project-planning/target/app.jar app.jar

CMD [ "sh", "-c", "java -Dserver.port=$PORT -jar /app.jar" ]
