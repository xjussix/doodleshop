FROM dockerfile/java
MAINTAINER mikael@sennerholm.net
ADD target/doodleshop.war /doodleshop.war
ENV DOODLE_ENV local
EXPOSE 8080
CMD java -Dse.caglabs.doodleshop.environment=${DOODLE_ENV} -jar /doodleshop.war
