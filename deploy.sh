#!/bin/bash
INSTANS=$1
VERSION=$2
PORTPREFIX=$3

sudo docker rm -d --name doodleshop-${INSTANS}
sudo docker run -d --name doodleshop-${INSTANS} -p ${PORTPREFIX}8080:8080 doodleshop:$VERSION

