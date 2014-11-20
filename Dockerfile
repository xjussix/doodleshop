FROM dockerfile/java
MAINTAINER mikael@sennerholm.net
COPY scripts/startdoodles.sh /startdoodles.sh
COPY target/doodleshop.war /doodleshop.war

ENV DOODLE_ENV local
# Service port
EXPOSE 8080
# Shutdown port
EXPOSE 9200
CMD bash /startdoodles.sh
