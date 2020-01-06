Feature: Modification of a user

  Background:
    Given there is a private user-mgmt server

  Scenario: Successful user modification
    Given I have a valid user payload
    When I PUT it to the /api/private/user endpoint
    Then I receive a 200 status code
    And I receive the updated user