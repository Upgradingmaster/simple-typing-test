#!/bin/bash

java -jar "./lib/junit-platform-console-standalone-1.10.2.jar" execute -cp "target" -cp lib/json-20240303.jar -cp lib/junit-platform-console-standalone-1.10.2.jar --scan-classpath

# {--select-package persistence/...    |    --scan-classpath}

