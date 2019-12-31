Feature: Authentication of a user

  Background:
    Given there is a user-mgmt server

  Scenario: Authenticate user
    Given I have an identifier payload
    When I POST it to the /api/public/authenticate endpoint
    Then I receive a 200 status code