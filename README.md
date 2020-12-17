# Springbooks

This is a Java 11 project that exposes a REST API documented with [OpenAPI](https://www.openapis.org/), developed with [SpringBoot](https://spring.io/projects/spring-boot) 2.4.0. All the information is stored in a [MySQL](https://www.mysql.com/) database.

It implements the following [scenario](doc/scenario.md). It has been developed using [Spring Tool Suite 4](https://spring.io/tools) as IDE.

## Installation

```
$ mvn clean install
```

## Deployment

Before deploy the application, is needed to has a available MySQL database, the most easy way is with [docker](https://www.docker.com/) executing the following command:

```
$ docker run --rm -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=posts -p 3306:3306 -d --name mysql-springbooks mysql:8.0.22
```

Also is required to create the ```books``` schema in MySQL database, this action can be performed using [MySQL Workbech](https://www.mysql.com/products/workbench).

Once we have it ready, we launch the application:

```
$ mvn spring-boot:run
```

## Testing

OpenAPI GUI is included in order to test that the application covers the proposed scenario: [http://localhost:8080/springbooks-manager-api.html](http://localhost:8080/springbooks-manager-api.html)

![SPRINGBOOKS OPEN API](doc/img/springbooks-openapi.png)
