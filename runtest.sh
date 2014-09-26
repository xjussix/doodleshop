#!/bin/bash
# Deploy and run smoketest on doodle
export INSTANS=${DOODLE_ENV}
export VERSION=${GO_PIPELINE_LABEL}
export PORTPREFIX=$1
export PORT=8080

set -e
echo "Running smoketests"
bash smoketest.sh >/dev/null 2>&1

for f in tests/${INSTANS}/*.sh
do
    echo "Processing $f file ..."
    bash $f
    echo "Done with $f"
done

