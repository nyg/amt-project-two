#!/usr/bin/env sh
# Script to build maven projects.
# Needs to be executed before docker-compose.

rootdir=$PWD

cd "$rootdir"/api-user-mgmt/server
mvn clean package

cd "$rootdir"/api-user-mgmt/specs
mvn clean package -DskipTests

# cd "$rootdir"/api-business/server
# mvn clean package
#
# cd "$rootdir"/api-business/specs
# mvn clean package -DskipTests
