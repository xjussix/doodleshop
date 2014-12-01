## Doodleshop Continuous Delivery Demo Application

Features:

* Spring Boot runnable application with embedded Tomcat and deployable war
* Spring Boot Actuator with production grade services
* Automatic versioning in MANIFEST.MF and in resource /appversion read from build pipeline environment variable
* Environment detection using environment variable <code>se.caglabs.doodleshop.environment</code>
* Environment specific configuration and log configuration using Spring profiles (application.yaml)
* Centralized logging with Logstash and rolling file logging
* Integration tests
* Flyway DB setup and migration
* Database H2, embedded if not specified external in application.yaml
 
### Build

<code>$ mvn install</code>

### Run Integration tests

<code>$ mvn -Pint-test verify</code>

### Run

<code>$ java -jar doodleshop.war</code>

For specific environment, specify:

<code>$ java -Dse.caglabs.doodleshop.environment=man -jar doodleshop.war</code>

where <code>se.caglabs.doodleshop.environment</code> is (local | autosmall | autolarge | man | prod)

### Test

The application has a few simple endpoints where you can store and read Doodles.

Create a doodle:
<code>$ curl -H "Content-Type: application/json" -X POST -d '{"author":"Hoomer","message":"Ummm..."}' http://localhost:8080/doodles</code>

Read created doodles:
<code>$ curl http://localhost:8080/doodles?author=Homer</code>

Have fun,
Daniel Marell

Trigger build 7
