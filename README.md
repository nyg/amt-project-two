# AMT — Project Two

* **Date:** 19.01.2020
* **Authors:** Nikolaos Garanis, Samuel Mettler.

## Introduction

> *See [here](https://github.com/nyg/amt-project-one/blob/master/README.md) for the first project's README.*

We have used the same business model as the first project, i.e. **customers** who can put **articles** in their shopping **cart**.

## Architecture

As requested, we have two APIs. Each is in its own folder and is composed of two parts: the server (Spring Boot) and the specs (containinig the Cucumber tests). The user management API is located in the `api-user-mgmt` folder and the business API in the `api-business`.

Each API has a Dockerfile which packages the corresponding Spring Boot server. These Dockerfiles are located in the `docker-images` folder.

## Functionnalities

### User management API

This API has two public endoints and a private one.

1. `/public/authenticate` allows a user to authenticate himself by sending (POST) his email and corresponding password.
2. `/public/users` allows anyone to register himself by providing (POST) his email, password, first name and last name. The account must then be activated by an administrator using the following end point.
3. `/private/user` allows a user to modify his account information. An administrator can modify user information of accounts other than his own, and only an administrator can modify the `active` and `admin` values of an account.

### Business API

TODO

## Running the project

```sh
# First build the server projects.
./build-servers.sh

# Then launch the containers with docker-compose.
docker-compose up --build
```

The APIs will be available at http://localhost:8898/api/mgmt and http://localhost:8898/api/business.

## Testing the project

Both APIs can be tested by running the `test-specs.sh` script. API servers must be running for the tests to succeed. By default, the tests will be ran against http://localhost:8080/api/xxx. But in the script, the Maven profile *custom* is used, which allows use to define a custom host and port for the API server. They are currently set to the values in the `docker-compose.yml`.

```sh
./test-specs.sh
```
