package stepdefination;

import adidas.api.utilities.RestApi;

import api.pojo.Category;
import api.pojo.Root;
import api.pojo.Tag;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.get;


public class petstoresteps {
    Scenario scenario;
    RestApi api;
    String petId = "";


    @Before("@api")
    public void beforeApi(Scenario scenario) {
        this.scenario = scenario;
        api = new RestApi();
    }

    // this method gets all the available pets from the store and validates the returned list contains only available stataus
    @Given("Get available pets based on status {string}")
    public void get_available_pets_based_on_status(String status) {
        scenario.log("GET all pets with " + status + " status");
        Response getResponse = api.PetGETByStatus(status);
        Assert.assertTrue("GET Pet by Status Check: " + status, getResponse.statusCode() == 200);
        Root[] root = getResponse.getBody().as(Root[].class);
        scenario.log("GET response: " + getResponse.asString());
        for (Root profile : root) {
            Assert.assertEquals("Verify Status", status, profile.getStatus());

        }


    }
    // this method creates a pet and checks if the created pet present in the DB
    @Given("Creates a new pet with status as {string}")
    public void creates_a_new_pet_with_status_as(String status) {
        petId = RandomStringUtils.randomNumeric(8);
        JSONObject payload = api.createpetpayload(status,petId);
        scenario.log("PET API Payload: " + payload.toString());
        Response PostResponse= api.PetCreation(payload.toString());
        System.out.println(PostResponse.statusCode());
        Assert.assertTrue("POST-Pet Create response code check : " + status, PostResponse.statusCode() == 200);
        scenario.log("Create PET API response: " + PostResponse.asString());
        String idfromPost = PostResponse.body().jsonPath().getString("id");
        Response Petbyidresponse=api.Petbyid(idfromPost);
        Assert.assertTrue("get pet by id api status verification  " + idfromPost, Petbyidresponse.statusCode() == 200);
        scenario.log("Get Pet by id Api response: " + Petbyidresponse.asString());
        String idfromGet = Petbyidresponse.body().jsonPath().getString("id");
        String statuspetbyid=Petbyidresponse.body().jsonPath().getString("status");
        Assert.assertEquals("Comparing the id between creation and get api id´s" ,idfromPost,idfromGet);
        Assert.assertEquals("Comparing the status between creation and api response" ,statuspetbyid,"available");




    }

//updates the status of the pet and checks if its updated in another api
    @Given("Updates the pet status to {string}")
    public void updates_the_pet_status_to(String status) {

        Response updateresponse=api.UpdatePetstatus(petId,status);
        Assert.assertTrue("POST-Pet update response code check : " + status, updateresponse.statusCode() == 200);
        scenario.log("Create PET API response: " + updateresponse.asString());


        Response Petbyidresponse=api.Petbyid(updateresponse.body().jsonPath().getString("message"));
        Assert.assertTrue("get pet details by id : " + status, Petbyidresponse.statusCode() == 200);

        Assert.assertEquals("Comparing the status between creation and api response" ,Petbyidresponse.body().jsonPath().getString("status"),"sold");

    }
    @Given("Store manager Deletes the  pet")
    public void store_manager_deletes_the_pet() {
        Response deleteresponse=api.Deletepet(petId);
        scenario.log("Delete PET API response: " + deleteresponse.asString());
        Assert.assertTrue("Delete PET API : ", deleteresponse.statusCode() == 200);
    }
    @Then("Verify if pet is deleted")
    public void verify_if_pet_is_deleted() {
        Response Petbyidresponse=api.Petbyid(petId);
        Assert.assertTrue("PET API : ", Petbyidresponse.statusCode() == 404);
        scenario.log("PET API by id response: " + Petbyidresponse.asString());
        Assert.assertEquals("Comparing the error message" ,Petbyidresponse.body().jsonPath().getString("message"),"Pet not found");
    }

}


