#!/bin/sh

cd ./spring-app
mvn clean install

docker stop cakehouse-frontend
docker stop cakehouse-backend
docker stop cakehouse-database

docker rm cakehouse-frontend
docker rm cakehouse-backend
docker rm cakehouse-database

docker image rm -f cakehouse-backend:latest
docker image rm -f cakehouse-frontend:latest


docker build \
  --build-arg JAR_FILE=`ls target/*.jar` \
  --build-arg PORT=8080 \
  --tag cakehouse-backend:latest \
  .

cd ..

cd react-app
docker build \
    --tag cakehouse-frontend:latest \
    .

cd ..

docker pull mysql:latest

docker-compose up