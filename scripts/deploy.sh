#!/bin/bash
INSTANCE=${DOODLE_ENV}
VERSION=${GO_PIPELINE_LABEL}
PORT_PREFIX=$1
echo running sudo docker rm -f doodleshop-${INSTANCE}
sudo docker rm -f doodleshop-${INSTANCE}
echo running sudo docker run -d --name doodleshop-${INSTANCE} -p ${PORT_PREFIX}8080:8080 doodleshop:$VERSION
sudo docker run -d --name doodleshop-${INSTANCE} -p ${PORT_PREFIX}8080:8080 doodleshop:$VERSION

