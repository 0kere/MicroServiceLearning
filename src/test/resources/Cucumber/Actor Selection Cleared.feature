Feature: Actor selection is cleared
  Background:
    Given The application is running
    And an actor is selected from the dropdown

  Scenario: Verifying that when the user clears the actor selection the actor information is no longer shown
    When the x is clicked on the search bar
    Then the actor information should be hidden
    And the search bar should no longer display the actor name