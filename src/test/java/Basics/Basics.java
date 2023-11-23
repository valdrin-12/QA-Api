package Basics;

import Payload.Payload;
import Payload.ReUsableMethods;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Basics {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
       // Payload payload=new Payload();
       String response= given()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(Payload.addPlace())
                .when()
                .post("maps/api/place/add/json")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .body("scope", equalTo("APP")).header("server","Apache/2.4.52 (Ubuntu)")
                .extract().response().asString();
       System.out.println("this is response in post"+ response);
       JsonPath jsonPath = new JsonPath(response);
       String placeId= jsonPath.getString("place_id");
       System.out.println(placeId);
       String newAddress = "Summer Walk, Africa";

      String putResponse= given().log().all().queryParam("key","qaclick123")
                .header("Content-Type", "application/json").body("{\r\n" +
                        "\"place_id\":\""+placeId+"\",\r\n" +
                        "\"address\":\""+newAddress+"\",\r\n" +
                        "\"key\":\"qaclick123\"\r\n" +
                        "}").
                when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200)
                .body("msg", equalTo("Address successfully updated")).extract().response().asString();
        System.out.println("this is response in PUT"+ putResponse);
        System.out.println("this is placeId in PUT  "+placeId);
        //Get Place
        String getPlaceResponse =given().log().all().queryParam("key","qaclick123")
                .queryParam("place_id",placeId).when().get("maps/api/place/get/json").then()
                .assertThat().log().all().statusCode(200).extract().response().asString();
       JsonPath js1= ReUsableMethods.rawToJson(getPlaceResponse);

        String actualAddress=js1.getString("address");
        System.out.println("this is actual address after we have updated with new address"+ " " + actualAddress);
        Assert.assertEquals(actualAddress,newAddress);

    }
}
