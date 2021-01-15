# quarkus-cafe-web

This is the web frontend for the Quarkus Coffeeshop Application

Orders can be placed through the web UI or a REST endpoint "/order"

## Running locally

## Local Development

__NOTE__: You have to install the domain objects to run the services and the test utilities to run the tests.  See [Building](building) 

### Attaching a debugger

By default Quarkus listensn on port 5005 for a debugger.  You can change this by appending the flag, "-Ddebug<<PORT NUMBER>>" as in the below examples.  The parameter is optional, of course

### Web
```shell script
export KAFKA_BOOTSTRAP_URLS=localhost:9092 STREAM_URL=http://localhost:8080/dashboard/stream CORS_ORIGINS=http://localhost:8080 STORE_ID=CHARLOTTE
./mvnw clean compile quarkus:dev
```

### Kafka and PostgreSQL
This service depends on Kafka and PostgreSQL both of which are started by the Docker Compose file in the support project

### Environment Variables

This services uses the following environment variables:
* KAFKA_BOOTSTRAP_SERVERS
* STREAM_URL
* CORS_ORIGINS

You can set them for local development with the following:

```shell script
export KAFKA_BOOTSTRAP_SERVERS=localhost:9092 STREAM_URL=http://localhost:8080/dashboard/stream CORS_ORIGINS=http://localhost:8080
```

### Running Locally
```shell
export KAFKA_BOOTSTRAP_URLS=localhost:9092 STREAM_URL=http://localhost:8080/dashboard/stream CORS_ORIGINS=http://localhost:8080
./mvnw clean package -Pnative -Dquarkus.native.container-build=true
docker build -f src/main/docker/Dockerfile.native -t <<DOCKER_HUB_ID>>/quarkuscoffeeshop-web .
docker run -i --network="host" -e STREAM_URL=${STREAM_URL} -e CORS_ORIGINS=${CORS_ORIGINS} -e KAFKA_BOOTSTRAP_URLS=${KAFKA_BOOTSTRAP_URLS} <<DOCKER_ID>>/quarkuscoffeeshop-web:latest
docker images -a | grep web
docker tag <<RESULT>> <<DOCKER_HUB_ID>>/quarkus-cafe-web:<<VERSION>>
```

### pgAdmin

The docker-compose file starts an instance of pgAdmin4.  You can login with:
* quarkus.cafe@redhat.com/redhat-20

You will need to create a connection to the Crunchy PostgreSQL database.  Use the following values:
* General 
** Name: pg10
* Connection
** Host: crunchy
** Port: 5432
** Maintenance database: postgres
** Username: postgres
** Password: redhat-20

The settings are not currently persisted across restarts so they will have to be recreated each time "docker-compose up" is run

## Packaging the native application

```shell
./mvnw clean package -Pnative -Dquarkus.native.container-build=true
docker build -f src/main/docker/Dockerfile.native -t <<DOCKER_HUB_ID>>/quarkuscoffeeshop-web .
export KAFKA_BOOTSTRAP_URLS=localhost:9092 STREAM_URL=http://localhost:8080/dashboard/stream CORS_ORIGINS=http://localhost:8080
docker run -i --network="host" -e KAFKA_BOOTSTRAP_URLS=${KAFKA_BOOTSTRAP_URLS} -e STREAM_URL=${STREAM_URL} -e CORS_ORIGINS=${CORS_ORIGINS} <<DOCKER_HUB_ID>>/quarkuscoffeeshop-counter:latest
docker images -a | grep web
docker tag <<RESULT>> <<DOCKER_HUB_ID>>/quarkuscoffeeshop-web:<<VERSION>>
```
## CHANGELOG

3.2.0
* Added "price" and "orderSource" as values to the JSON
* Updated to new domain model

3.1.0
* refactored package "io.quarkuscoffeeshop.core" to "io.quarkuscoffeeshop.counter"
* Added persistence for events in orders topic


