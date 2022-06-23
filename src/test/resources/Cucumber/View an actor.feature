Feature: View an actor
  As a user I would like to be able to view an actor

  Scenario: User makes call to GET /actor/{id}
    Given I have the actors id
    When I make call to /actor/{id}
    Then I receive the actor information