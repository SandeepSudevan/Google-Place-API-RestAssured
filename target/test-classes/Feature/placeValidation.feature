Feature: Validating Place API

@AddPlace @Regression
Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
Given Add place payload with "<name>" "<address>" "<language>"
When user calls "AddPlaceAPI" with "POST" htpp request
Then the API call success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_id created with "<name>" maps to "getPlaceAPI"

Examples:
|name  |address|language|
|Daszio|Chennai|English |
#|Sand  |Kerala |Tamil   |

@DeletePlace @Regression
Scenario: Verify if Delete Place functionality is working
Given Delete Place Payload
When user calls "deletePlaceAPI" with "POST" htpp request
Then the API call success with status code 200
And "status" in response body is "OK"

