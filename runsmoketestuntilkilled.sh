#!/bin/bash

while `/bin/true`
do
    echo try to run smoketest.sh
    if `bash smoketest.sh`; then break; fi
    sleep 3
done

