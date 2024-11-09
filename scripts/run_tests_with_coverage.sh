#!/bin/bash

# Scan coverage
java -javaagent:./lib/jacocoagent.jar=destfile=./jacoco/jacoco.exec -cp "lib/*:target" org.junit.platform.console.ConsoleLauncher --class-path target --scan-class-path 

# Generate output site
java -jar ./lib/jacococli.jar report ./jacoco/jacoco.exec --classfiles ./target --sourcefiles ./src/main --html ./jacoco/site/

# Open in browser
xdg-open ./jacoco/site/index.html
