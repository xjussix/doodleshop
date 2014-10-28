== Mysql setup ==

Todo: Changed to H2. Describe H2 instead.

CREATE DATABASE doodleshop;
GRANT ALL PRIVILEGES ON *.* TO 'doodleshop'@'localhost' identified by 'doodleshop' WITH GRANT OPTION;

== Todo ==

- Build number
- flywaydb migration

== Run ==

java -Dse.caglabs.doodleshop.environment=(local|autosmall|autolarge|man|prod) -jar /doodleshop.war

== Test ==

Create a doodle:
$ curl -H "Content-Type: application/json" -X POST -d '{"author":"Hoomer","message":"Ummm..."}' http://localhost:8080/doodles

Read created doodles:
$ curl http://localhost:8080/doodles?author=Homer
