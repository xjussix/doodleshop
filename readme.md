== Mysql setup ==
CREATE DATABASE doodleshop;
GRANT ALL PRIVILEGES ON *.* TO 'doodleshop'@'localhost' identified by 'doodleshop' WITH GRANT OPTION;

== Todo ==
-Build number
-flywaydb migration

== Run ==
java -jar doodleshop.war

== Test ==
Create a doodle:
$ curl -i -H "Content-Type: application/json" -X POST -d '{"created":1399581007414,"author":"Hoomer","message":"Ummm..."}' http://localhost:8080/doodles

Read created doodles:
$ curl http://localhost:8080/doodles?author=Homer
