#!/bin/bash
INSTANS=$1
VERSION=$2
PORTPREFIX=$3
echo running sudo docker rm -f--name doodleshop-${INSTANS}
sudo docker rm -f--name doodleshop-${INSTANS}
echo running sudo docker run -d --name doodleshop-${INSTANS} -p ${PORTPREFIX}8080:8080 doodleshop:$VERSION
sudo docker run -d --name doodleshop-${INSTANS} -p ${PORTPREFIX}8080:8080 doodleshop:$VERSION

