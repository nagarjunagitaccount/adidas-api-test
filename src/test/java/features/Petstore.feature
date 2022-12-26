Feature: Petstore
  Verify petstore api's
  @api
  Scenario Outline: Validate retrieve all pets,create,update and delete of pets
    Given Get available pets based on status "<status>"
    And Creates a new pet with status as "<create_status>"
    And Updates the pet status to "<update_status>"
    And Store manager Deletes the  pet
    Then Verify if pet is deleted
    Examples:
      | status |create_status|update_status|
      | available|available|sold|


