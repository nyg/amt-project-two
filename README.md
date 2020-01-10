# AMT — Project Two

* **Date:** 19.01.2020
* **Authors:** Nikolaos Garanis, Samuel Mettler.

## Introduction

> *See [here](https://github.com/nyg/amt-project-one/blob/master/README.md) for the first project's README.*

We have used the same business model as the first project, i.e. **customers** who can put **articles** in their **cart**.

## Architecture

As requested, we have two APIs. Each is in its own folder and is composed of two parts: the server (Spring Boot) and the specs (containinig the Cucumber tests). The user management API is located in the `api-user-mgmt` folder and the business API in the `api-business`.

Each API has a Dockerfile which packages the corresponding Spring Boot server. These Dockerfiles are located in the `docker-images` folder.

## Running the project

```sh
# First build the server projects.
./build-servers.sh

# Then launch the containers with docker-compose.
docker-compose up --build
```

The APIs will be available at http://localhost:80/api.

## Testing the project

```sh
# Server instances must be running before launching this script.
# For example by following the steps in `Running the project'.
./test-specs.sh
```
