#!/bin/bash
# Deploy and run smoketest on doodle
INSTANS=${DOODLE_ENV}
VERSION=${GO_PIPELINE_LABEL}
PORTPREFIX=$1
PORT=8080

set -e
# Kill old instans
echo running sudo docker rm -f doodleshop-${INSTANS}
sudo docker rm -f doodleshop-${INSTANS} || /bin/true
# Start a new one
echo running sudo docker run -d -e "se.caglabs.doodleshop.environmen=${INSTANS}" --name doodleshop-${INSTANS} -p ${PORTPREFIX}${PORT}:${PORT} doodleshop:$VERSION
sudo docker run -d -e "se.caglabs.doodleshop.environmen=${INSTANS}" --name doodleshop-${INSTANS} -p ${PORTPREFIX}${PORT}:${PORT} doodleshop:$VERSION
# Sleep some time before checking that it's working
sleep 2
bash smoketest.sh 

