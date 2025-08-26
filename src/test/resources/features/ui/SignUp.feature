Feature: all sign up and sign in test cases

  @successfulSignUp @smoke @regression @CSHW-123
  Scenario: Verify user can successfully sign up
    Given user is on "https://cashwise.us/"
    When user clicks on sign up button
    And user provides email "admin116@gmail.com", password "xyz789", confirm password "xyz789"
    And user clicks on continue
    Then verify sign up form appeared
    And user provides first name, last name, name of business, area of business, currency
    And user clicks on final green sign up button
    Then verify user is signed in

