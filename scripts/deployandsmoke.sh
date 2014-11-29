#!/bin/bash
# Deploy and run smoketest on doodle
INSTANCE=${DOODLE_ENV}
PORT_PREFIX=$1
PORT=8080

set -e
# Newversion
#VERSION=${GO_PIPELINE_LABEL} (Old, may not always working futher down in the dependency chain)
# http://stackoverflow.com/questions/59895/can-a-bash-script-tell-what-directory-its-stored-in
SCRIPT_DIR=$(dirname $(readlink -f $0))"
VERSION=`grep AppVersion ${SCRIPT_DIR}/../BuildInfo.propertiesproperties  | awk -F'=' '{print $2}'`
echo "Deploy Version ${VERSION}"
# Kill old instans
echo running sudo docker rm -f doodleshop-${INSTANCE}
sudo docker rm -f doodleshop-${INSTANCE} || /bin/true
# Start a new one
echo running sudo docker run -d -e "DOODLE_ENV=${DOODLE_ENV}" --name doodleshop-${INSTANCE} -p ${PORT_PREFIX}${PORT}:${PORT} doodleshop:$VERSION
sudo docker run -d -e "DOODLE_ENV=${DOODLE_ENV}" --name doodleshop-${INSTANCE} -p ${PORT_PREFIX}${PORT}:${PORT} doodleshop:$VERSION
# Sleep some time before checking that it's working
timeout 3m bash runsmoketestuntilkilled.sh

