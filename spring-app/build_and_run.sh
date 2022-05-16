#!/bin/sh

# Build project and its image
mvn clean install

JAR_FILE=`ls target/*.jar`
echo $JAR_FILE

docker build \
  --build-arg JAR_FILE=$JAR_FILE \
  -t cakehouse-backend:latest .

# Run application with local mysql db
docker-compose up