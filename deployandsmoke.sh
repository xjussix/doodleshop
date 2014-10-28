#!/bin/bash
# Deploy and run smoketest on doodle
INSTANCE=${DOODLE_ENV}
VERSION=${GO_PIPELINE_LABEL}
PORT_PREFIX=$1
PORT=8080

set -e
# Kill old instans
echo running sudo docker rm -f doodleshop-${INSTANCE}
sudo docker rm -f doodleshop-${INSTANCE} || /bin/true
# Start a new one
echo running sudo docker run -d -e "DOODLE_ENV=${DOODLE_ENV}" --name doodleshop-${INSTANCE} -p ${PORT_PREFIX}${PORT}:${PORT} doodleshop:$VERSION
sudo docker run -d -e "DOODLE_ENV=${DOODLE_ENV}" --name doodleshop-${INSTANCE} -p ${PORT_PREFIX}${PORT}:${PORT} doodleshop:$VERSION
# Sleep some time before checking that it's working
timeout 3m bash runsmoketestuntilkilled.sh

