# CS309_SUSTech_Store_Backend
The backend API for the sustech store

To construct the running enviroment, install **MySQL** and **Redis**.

First create database **mmall** in mysql, and running the **mmall.sql** to create tables.

The configuration file is at src/main/resources/application.yml. You can change the password as your own password.
```
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mmall?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver
```

Run the **StoreApplication.java** to start the service. 
And the API document is at http://localhost:8181/swagger-ui.html# by defalut.
