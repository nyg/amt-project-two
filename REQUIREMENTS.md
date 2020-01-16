# Teaching-HEIGVD-AMT-2019-Project-Two
## Objectives – OK

The objectives of this project is to design, specify, implement and validate **2 RESTful APIs** (you can think of them as 2 "micro-services"), using a set of technologies that build upon or complement Java EE standards. Namely, the goal is to use:

* **Spring Boot**, **Spring Data**, **Spring MVC** and **Spring Data** for the implementation of the endpoints and of the persistence;
* **Swagger** (**Open API**) to create a formal documentation of the REST APIs (this formal documentation has to be used in the development cycle);
* JSON Web Tokens (**JWT**) to secure the RESTful endpoints;
* **CucumberJVM** to implement BDD tests.

## Functional requirements

* Design, specify and implement **a first API** that is used to **manage** user accounts.
  * ~~Every account has at least an e-mail (used as the primary ID), a first name, a last name and a password.~~
  * ~~The API must also allow the user to change its password.~~
  * ~~A user cannot change the password of someone else.~~
  * ~~The API also exposes an endpoint to authenticate a user: it returns a JWT token if the provided credentials are correct.~~
  * Specify, implement and validate at least one of these features:
    * NOT IMPLEMENTED: Only a user with an ADMIN role can create accounts.
    * ~~A user with an ADMIN role can block/unblock a user account; when blocked, the user cannot login. Be mindful of JWT tokens.~~
    * NOT IMPLEMENTED: A user needs to prove that he owns the e-mail address (by receiving an e-mail with a code).
    * NOT IMPLEMENTED: A user can ask to reset his password, which is done via e-mail (typical reset password).
* Like in the first project, **expose at least 3 entities through a second REST API** (one of them capturing the relationship between the two others; for instance, *Membership* would capture the relationship between *Person* and *Group*). You can use the same entities that you used in the first project, but do not have to.
  * The REST API must support CRUD operations on the 2 main entities; you have to specify what is the intended behavior when you delete an entity.
  * The REST API must provide a way to associate/de-associate a pair of two main entities.
  * it is up to you to define the structure of your payloads (DTOs), but you have to justify your choices in the report (and to explain what are the tradeoffs)
* The REST APIs must implement **pagination**. It is up to you to decide how the client and server negotiate the parameters, but you have to explain it in your documentation.

## Constraints – OK

- You HAVE TO use Spring Boot, Spring MVC and Spring Data.
- You MUST NOT use Spring Data REST (MUST NOT = you are not allowed).
- You MUST specify both APIs with Swagger / Open API.
- You MUST implement two Spring Boot projects, each producing a different .jar file.
- You must deliver a Docker Compose topology, with (at least):
  - A container with the first back-end
  - A container with the second back-end
  - A container with the RDBMS; every back-end should have its own database (no shared tables)
  - A container with Traefik, acting as a dynamic reverse proxy

## Non-functional requirements

* **Automation** – OK
  * It MUST be possible to build, run and test your project with minimal effort (you know how to use Docker Compose and how to write scripts)
* **Testing**
  * **BDD**. Implement comprehensive testing with CucumberJVM.
  * **Performance and load testing**. Implement JMeter tests for several use cases.
* **Documentation**
  * Document the decisions you made during the design of the API.
  * Document your implementation of the back-end APIs (how did you use the framework capabilities, what did you have to do to fix issues or implement special features).
  * Document what you have one to test and validate your project.
  * Document and comment your performance results (we want numbers, screenshots and an interpretation).

## Organization – OK

**Deliverables:**

* ~~Clean git repo, with clear instructions on the main README.md for how to build, run and test your application.~~
* ~~Report as a set of markdown files in a doc folder.~~
* ~~Links to the various markdown files from the main README.md files.~~
* What do we want to read in your report?
  * ~~**What** you have implemented (functional aspects). Tell us briefly about the business domain you have selected and describe your business model. A diagram showing the entities and their relationships will help. A couple of screenshots too.~~
  * **How** you have implemented it. Tell us briefly about the components you had to use across the tiers and if you encountered issues or made choices that you find interesting.
  * ~~You **testing strategy**: we want to see that you understand the role and value of the different types of automated tests. We want to see that you can explain what tools can be used t implement these types of tests. We want to have your opinion on the effectiveness of your test strategy (what do you like and what do you not like about your test suite?)~~
  * ~~In particular a detailed report about your **experiment** to answer the performance tests. We want a clear description of the experiment. We want numbers, graphs and explanations of what they mean.~~
  * A list of **known bugs and limitations**.

## Proposed timeline

**Week 3 (December 16th):**

* ~~Design, implement and validate the **user management** and **authentication** API.~~
* ~~Go back to the first endpoint implementation and **enforce security rules**.~~
* Implement BDD scenarios to validate that authentication and authorization rules work as expected.

**Week 5 (January 13th):**

* ~~Performance tests with JMeter~~
* ~~Final packaging and validation~~
* Documentation
