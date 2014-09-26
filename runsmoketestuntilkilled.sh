#!/bin/bash

while `/bin/true`
do
    echo try to run smoketest.sh
    if `bash smoketest.sh >/dev/null 2>&1`; then break; fi
    sleep 3
done

