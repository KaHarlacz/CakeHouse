# CakeHouse

This is my first app which I wrote using Spring framework and React.

## How to run this app

At this stage, it is possible to run this app on your machine but there is no database configured to store data. I will make this app run in docker containers which will solve this problem.

Environment variables:

REACT_APP_CAKEHOUSE_BACKEND_URL - URL which localize backend of the app

CAKEHOUSE_DATABASE_USERNAME
CAKEHOUSE_DATABASE_PASSWORD
CAKEHOUSE_DATABASE_URL

App can send email notifications to users whose recipes were commented.
To make this feature work below optional env variables should be passed:
CAKEHOUSE_EMAIL_ADDRESS
CAKEHOUSE_EMAIL_PASSWORD
CAKEHOUSE_EMAIL_HOST
CAKEHOUSE_EMAIL_SMTP_PORT


# Patterns used

Design patterns: 
  - Adapter
  - Decorator
  - Facade
  - Builder
  - Strategy
  - Singleton
  
Architecture: 
  - MVP

Others:
  - DTO
  - Repository

## Screenshots

### Main Page

![image](https://user-images.githubusercontent.com/57493754/150585388-a656eb75-20fa-4b2c-97df-760ce31f94f9.png)

![image](https://user-images.githubusercontent.com/57493754/150585449-11496502-a873-4bde-bbaf-8f1b5110806f.png)

### Discover Page

![image](https://user-images.githubusercontent.com/57493754/150585540-abe7edf9-1342-4e1e-a60a-7dab016a22dc.png)
