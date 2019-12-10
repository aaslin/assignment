README
============

## Build application
mvn clean install

## Run application
mvn spring-boot:run

## Read only user
username:user password:user

## Read/Write user
username:admin password:admin

## Populate with data
curl -X POST -H "Content-Type:application/json" -d "{\"title\": \"Toy Story\",\"duration\": 81}" http://admin:admin@localhost:8081/movies

## Or populate by running the script
./insert_test_data.sh

## Get all movies 
curl http://user:user@localhost:8081/movies

## Get all movies with formatted duration
curl http://user:user@localhost:8081/moviesformatted

## Find all movies with title containing pattern
curl http://user:user@localhost:8081/movies/search/findByTitleContaining?pattern=Tower

## Delete movie
curl -X DELETE http://admin:admin@localhost:8081/movies/1
