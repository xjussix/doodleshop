#!/bin/bash

PORT=$1
if ! `curl -f http://localhost:${PORT}/doodles?author=Homer | grep 'Ummmmm' >/dev/null`
then
    echo "Test failed if ! `curl http://localhost:${PORT}/doodles?author=Homer | grep 'Ummmmm' >/dev/null`"
    exit 1
fi
# More tests here!
exit 0
