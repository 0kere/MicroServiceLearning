Feature: View actor information
  Scenario: Verifying the dropdown works and returns the correct actor information
    Given The application is running
    When an actor is selected from the dropdown
    Then the actor information should be shown the to user