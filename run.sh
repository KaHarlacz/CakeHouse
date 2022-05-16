#!/bin/sh

cd spring-app
mvn clean install
docker build \
  --build-arg JAR_FILE=`ls target/*.jar` \
  -t cakehouse-backend:latest .

cd ..

cd react-app
docker build \
    -t cakehouse-frontend:latest .

cd ..

docker-compose up