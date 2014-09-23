FROM dockerfiles/java
MAINTAINER mikael@sennerholm.net
ADD doodleshop.war /doodleshop.war
EXPOSE 8080
CMD java -jar /doodleshop.war
