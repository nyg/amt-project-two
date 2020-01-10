#!/usr/bin/env sh
# Script to test the specs projects.
# Needs running instances of the servers.

rootdir=$PWD

cd "$rootdir"/api-user-mgmt/specs
mvn clean package

# cd "$rootdir"/api-business/specs
# mvn clean package
