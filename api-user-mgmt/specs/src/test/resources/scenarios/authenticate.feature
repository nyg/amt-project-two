Feature: Authentication of a user

  Background:
    Given there is a public user-mgmt server

  Scenario: Successful user authentication
    Given I have a valid identifier payload
    When I POST it to the /api/public/authenticate endpoint
    Then I receive a 200 status code

  Scenario: Failed user authentication
    Given I have an invalid identifier payload
    When I POST it to the /api/public/authenticate endpoint
    Then I receive a 401 status code
