@regression
Feature: end to end scenario to validate invoice on API and DB

  @getInvoice @smoke @api @jenkins
  Scenario: verify invoice details are displayed accurately
    Given base url "https://backend.cashwise.us/api/myaccount"
    And I have access
    And I have the endpoint "/invoices/1"
    And I have "page" with "1" as query param
    And I have "size" with "10" as query param
    When I send GET request
    And I retrieve id for "invoice_id"
    Then verify status code is 200
#    Then verify I have "invoice_title" with "DEVOPS invoice" in response body
#    And verify the invoice details in the response match the database






