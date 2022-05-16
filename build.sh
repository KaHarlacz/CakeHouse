docker stop cake_house_frontend
docker stop cake_house_backend
docker stop cake_house_mysql

docker container rm cake_house_mysql
docker container rm cake_house_backend
docker container rm cake_house_frontend

docker pull mysql:8.0
docker build -t cake_house_backend spring-app
docker build -t cake_house_frontend react-app

docker run -d --name cake_house_mysql -p 3307:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=cake_house mysql:8.0 
docker run -d --name cake_house_backend cake_house_backend:latest
docker run -d --name cake_house_frontend cake_house_frontend:latest