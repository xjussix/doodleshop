#!/bin/bash
INSTANS=${DOODLE_ENV}
VERSION=${GO_PIPELINE_LABEL}
PORTPREFIX=$1
echo running sudo docker rm -f doodleshop-${INSTANS}
sudo docker rm -f doodleshop-${INSTANS}
echo running sudo docker run -d --name doodleshop-${INSTANS} -p ${PORTPREFIX}8080:8080 doodleshop:$VERSION
sudo docker run -d --name doodleshop-${INSTANS} -p ${PORTPREFIX}8080:8080 doodleshop:$VERSION

