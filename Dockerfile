FROM openjdk:14

ENV ENVIRONMENT=prod

MAINTAINER Fabian Schmauder <fabian.schmauder@gmail.com>

ADD project-planning/target/project-planning.jar app.jar

CMD ["sh", "-c", "java -Dserver.port=$PORT -jar /app.jar"]
