#!/bin/bash

INSTANS=$DOODLE_ENV
PORT=8080

# Get the IPAdress of the docker instans
DOODLE_IP=`docker inspect doodleshop-${INSTANS} | grep IPAddress | awk -F'\"' '{print $4}'`
# Basic smoketest, TODO: should be changed to environment?
if ! `curl -f http://${DOODLE_IP}:${PORT}/doodles?author=Homer | grep 'Ummmmm' >/dev/null`
then
    echo "Test failed (curl http://${DOODLE_IP}:${PORT}/doodles?author=Homer | grep 'Ummmmm' >/dev/null)"
    exit 1
fi
# More tests here!
exit 0
