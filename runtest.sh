#!/bin/bash
# Deploy and run smoketest on doodle
INSTANS=${DOODLE_ENV}
VERSION=${GO_PIPELINE_LABEL}
PORTPREFIX=$1
PORT=8080

set -e
echo "Running smoketests"
bash smoketest.sh 

for f in tests/${INSTANS}/*.sh
do
    echo "Processing $f file ..."
    bash $f
    echo "Done with $f"
done

