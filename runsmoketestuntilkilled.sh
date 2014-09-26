#!/bin/bash

while `/bin/true`
do
    echo try to run smoketest.sh
    if `bash smoketest.sh >/dev/null`; then break; fi
    sleep 3
done

