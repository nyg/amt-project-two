#!/usr/bin/env sh
# Script to test the specs projects.
# Needs running instances of the servers (run docker-compose up).

rootdir=$PWD

cd "$rootdir"/api-user-mgmt/specs
mvn clean package -Pcustom -DAPI_MGMT_HOST=localhost -DAPI_MGMT_PORT=8898

#cd "$rootdir"/api-business/specs
#mvn clean package -Pcustom -DAPI_BUSINESS_HOST=localhost -DAPI_BUSINESS_PORT=8898
