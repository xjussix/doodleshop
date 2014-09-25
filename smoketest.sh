#!/bin/bash

INSTANS=$1
PORT=8080
IP=`docker inspect ${INSTANS} | grep IPAddress | awk -F'\"' '{print $4}'`
if ! `curl -f http://${IP}:${PORT}/doodles?author=Homer | grep 'Ummmmm' >/dev/null`
then
    echo "Test failed if ! `curl http://${IP}:${PORT}/doodles?author=Homer | grep 'Ummmmm' >/dev/null`"
    exit 1
fi
# More tests here!
exit 0
