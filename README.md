# meterservice

This spring boot application process meterin data via REST API. It saves data to external MySQL database created inside Docker container.

## Building (on Windows)
```
gradle clean build -x test
```
## Development
- Run the Docker development environment:
```
docker-compose up -d
```
This command will provide working environment with *mysql* images.

- Run commands in following order:
```
1. docker ps
2. docker exec -it <CONTAINER ID> bash
3. mysql -u root -p
4. (enter pass) root
5. mysql> CREATE DATABASE myDb;
6. mysql> USE myDb;
```
- Open another terminal/cmd and then:
```
java -jar path\to\project\meterservice\build\libs\meterservice-0.0.1-SNAPSHOT.jar
   (or inside root of project .\meterservice ) $ gradle bootRun
```
- Open web browser or some other API testing tool(Postman, ...), list of REST endpoints are in *resources/endpoints.txt*
 
