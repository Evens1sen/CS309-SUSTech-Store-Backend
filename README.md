# CS309_SUSTech_Store_Backend
The backend API for the sustech store

To construct the running enviroment, install **MySQL** and **Redis**
First create database mmall in mysql, and running the mmall.sql to create tables.

The configure file is at src/main/resources/application.yml
```
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mmall?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver
```
