Feature: Online shopping

  Background:
    Given there is a business server

  Scenario: Get articles
    When I GET it to the /articles endpoint
    Then I receive a 200 status code
    And I receive the articles