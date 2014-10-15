#!/bin/bash

echo "Executing Maven LargeTest for env:"
env
mvn verify -B
echo "--- END ---"
exit 0
