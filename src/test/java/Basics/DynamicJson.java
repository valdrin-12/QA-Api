package Basics;

import Payload.Payload;
import Payload.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson {
    @Test
    public void addBook(){
    RestAssured.baseURI="http://216.10.245.166";
    String response= given().log().all().header("Content-Type","application/json").body(Payload.addBookApi()).when()
            .post("Library/Addbook.php").then().log().all().assertThat().statusCode(200)
            .extract().response().asString();
        JsonPath js= ReUsableMethods.rawToJson(response);
        String id=js.get("ID");
        System.out.println(id);


}
}
