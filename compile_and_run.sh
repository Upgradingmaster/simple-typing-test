#!/bin/bash

source ./scripts/compile.sh

if [ $? -ne 0 ]; then
    echo "Compilation failed"
    exit 1
fi


source ./scripts/run.sh
