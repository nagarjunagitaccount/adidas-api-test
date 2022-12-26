package adidas.api.utilities;

import java.security.PublicKey;
import java.util.*;

import api.pojo.Category;
import api.pojo.Root;
import api.pojo.Tag;
import api.resources.resources;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import adidas.api.utilities.DataAccessConf;
import org.json.JSONObject;

public class RestApi {


    private RequestSpecification specification;

    public RestApi() {
        //RestAssured.baseURI = DataAccessConf.get().gethost();
        specification = RestAssured.given()
                .header("Accept", "application/json")
                .baseUri(DataAccessConf.get().gethost());

    }

    public Response PetGETByStatus(String status) {
        String getUrl = resources.getallpetsbystatus();

        Response response = RestAssured.given().spec(specification)
                .queryParam("status", "available")
                .get(getUrl).andReturn();
        return response;
    }

    public Response PetCreation(String payload) {
        //headers.put("Content-Type", "application/json");
        String getres = resources.getcreatepet();
        Response response = RestAssured.given().spec(specification).header("Content-Type", "application/json").body(payload).post(getres).andReturn();
        return response;

    }

    public JSONObject createpetpayload(String status,String petid) {
        List<Tag> tags = new ArrayList<>();
        List<String> photo = new ArrayList<>();
        photo.add("photoUrls");

        Tag tag = new Tag(1233, "tag");
        tags.add(tag);
        Category cat = new Category(123, "Name");

        Root root = new Root(petid, cat, "Petname", photo, tags, status);
        JSONObject jsonObj = new JSONObject(root);
        System.out.println(jsonObj);
        return jsonObj;
    }

    public Response Petbyid(String id) {
        String getres = resources.getpetbyid();
        Response response = RestAssured.given().pathParam("id", id).spec(specification)
                .get(getres).andReturn();
        return response;
    }

    public Response UpdatePetstatus(String petid, String status) {

        String getres = resources.getpetbyid();

        Response response = RestAssured.given().pathParam("id", petid).spec(specification).header("Content-Type", "application/x-www-form-urlencoded").body("status=" + status).post(getres).andReturn();
        return response;
    }
    public  Response Deletepet(String petid){
        String get=resources.getpetbyid();
        Response response = RestAssured.given().pathParam("id", petid).spec(specification)
                .delete(get).andReturn();
        return response;
    }

}
