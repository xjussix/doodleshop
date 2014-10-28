#!/bin/bash

echo "Executing Maven LargeTest for env:"
env
mvn -B -Pint-test verify
echo "--- END ---"
exit 0
