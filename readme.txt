/usr/local/mysql-8.0.25-macos11-x86_64/bin/mysql -u root -p
root@1234

 create user craft identified by "craft";
 GRANT ALL PRIVILEGES ON * . * TO craft;
 FLUSH PRIVILEGES;
 
 CREATE DATABASE conversion;
 
 
 use conversion;
create table exchange_value(
   id INT NOT NULL AUTO_INCREMENT,
   from_currency VARCHAR(10) NOT NULL,
   to_currency VARCHAR(10) NOT NULL,
   conversion_multiple double NOT NULL, 
   PRIMARY KEY ( id )
);


mvn spring-boot:build-image -DskipTests

docker container ls -a

docker network create currency-network
docker run -d --rm -p 8761:8761 --name eureka-server  --network currency-network craft/craft-feed-naming-server:0.0.1-SNAPSHOT

docker run -d --rm -p 8000:8000 --name exchange-service  --network currency-network craft/craft-feed-exchange-service:0.0.1-SNAPSHOT





 mvn clean package
java -Djarmode=layertools -jar target/craft-feed-exchange-service-0.0.1-SNAPSHOT.jar list
docker build . --tag dockerfile/craft/craft-feed-exchange-service:0.0.1-SNAPSHOT

docker run -d --rm -p 8761:8761 --name eureka-server  --network currency-network dockerfile/craft/craft-feed-naming-server:0.0.1-SNAPSHOT

docker run -d --rm -p 8000:8000 --name exchange-service  --network currency-network dockerfile/craft/craft-feed-exchange-service:0.0.1-SNAPSHOT
docker run -d --rm -p 8100:8100 --name conversion-service  --network currency-network dockerfile/craft/craft-feed-conversion-service:0.0.1-SNAPSHOT


CREATE DATABASE userfeed;
 
 
use userfeed;
create table enduser(
   id INT NOT NULL AUTO_INCREMENT,
   name VARCHAR(50) NOT NULL,
   birthday timestamp NOT NULL,
   PRIMARY KEY ( id )
);


web actuator mysql jpa devtools test

http://localhost:8000/enduserspagination?pageNo=0&pageSize=2&sortBy=name