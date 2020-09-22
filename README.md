# quarkus-cafe-web

This is the web frontend for the Quarkus Coffeeshop Application

Orders can be placed through the web UI or a REST endpoint "/order"

## Running locally

See [Working Locally](../WORKING-LOCALLY.md) for details

### Kafka and PostgreSQL
This service depends on Kafka and PostgreSQL both of which are started by the Docker Compose file in the support project


### Running Locally
```shell
./mvnw clean package -Pnative -Dquarkus.native.container-build=true
docker build -f src/main/docker/Dockerfile.native -t <<DOCKER_HUB_ID>>/quarkus-cafe-web .
docker run -i --network="host" -e STREAM_URL=${STREAM_URL} -e CORS_ORIGINS=${CORS_ORIGINS} -e KAFKA_BOOTSTRAP_URLS=${KAFKA_BOOTSTRAP_URLS} quarkus-cafe-demo/quarkus-cafe-web:latest
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



