# Unicorn App

### Execute test cases using maven
```java
mvn clean verify
```

### Start using docker composer
* Create build first
```shell
mvn clean install -DskipTests 
```
* Create docker image using docker composer
```shell
docker-compose up -d --force-recreate --no-deps --build unicorn-app
```

### Swagger UI
[Swagger-UI] (http://localhost:8087/swagger-ui/)

