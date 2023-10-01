#!/bin/sh

sh scripts/makejar.sh

cd $(dirname $0)/..

java -jar dist/lsystem.jar

