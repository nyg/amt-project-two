Feature: Modification of a user

  Background:
    Given there is a user-mgmt server

  Scenario: Successful user modification
    Given I have a valid user payload
    When I PUT it to the /api/private/user endpoint
    Then I receive a 200 status code
    And I receive the updated user

  Scenario: Successful user creation
    Given I have a valid user creation payload
    When I POST it to the /api/public/users endpoint
    Then I receive a 201 status code

  Scenario: Already existing user creation
    Given I have an already existing user creation payload
    When I POST it to the /api/public/users endpoint
    Then I receive a 409 status code

  Scenario: Invalid user creation input
    Given I have an invalid user creation payload
    When I POST it to the /api/public/users endpoint
    Then I receive a 400 status code
