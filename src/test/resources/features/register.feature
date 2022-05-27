@register
Feature: User Register
    Scenario: User register successfully
        Given user is in home page
        When user goes to register page
        And user in register page inserts email 'email_test@email.com'
        And user press Create an account button
        Then detailed form appears and user inserts data 'user_case_success'
        And press register button
        Then logged home page is shown

    Scenario: Tries to register user with invalid email
        Given user is in home page
        When user goes to register page
        And user in register page inserts email 'invalid_email'
        And user press Create an account button
        Then email error message appears

    Scenario: User register with missing required fields
        Given user is in home page
        When user goes to register page
        And user in register page inserts email 'email_test@email.com'
        And user press Create an account button
        Then detailed form appears and user inserts data 'user_case_missing_required'
        And press register button
        Then fields error message appears