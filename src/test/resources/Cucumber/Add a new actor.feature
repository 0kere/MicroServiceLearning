Feature: Add new actor
  As a user I would like to add a new actor into my table

  Scenario: I successfully add an actor to the table
    Given I have the actors information
    When I add the actor information to the table
    Then I get the return string saved

  Scenario: I don't have enough information
    Given I have an actors first name
    And I dont have their last name
    When I try add the actor to the table
    Then I get an error