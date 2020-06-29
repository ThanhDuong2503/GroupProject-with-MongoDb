FROM openjdk:14

ENV ENVIRONMENT=prod

<<<<<<< HEAD
MAINTAINER Thanh Duong <thanh.duong2503@hotmail.de>

ADD project-planning/target/project-planning.jar app.jar

CMD [ "sh", "-c", "java -Dserver.port=$PORT -Dspring.data.mongodb.uri=$MONGODB_URI -jar /app.jar" ]
=======
MAINTAINER Fabian Schmauder <fabian.schmauder@gmail.com>

ADD project-planning/target/project-planning.jar app.jar

CMD ["sh", "-c", "java -Dserver.port=$PORT -Dspring.data.mongodb.uri=$MONGODB_URI -jar /app.jar"]
>>>>>>> d612338ba8db4979eea4d6becb3a4d4b003bde14
