package myReqresHW3;



import io.restassured.http.ContentType;

import io.restassured.response.Response;

import org.apache.tapestry5.json.JSONObject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



import static io.restassured.RestAssured.given;

public class postMethod extends AbstractTest {

    @DisplayName("Ошибка")
    @Test
    public void LOGIN22() {
        JSONObject requestBody = new JSONObject()

                .put("email", "eve.holt@reqres.in")
                .put("password", "pistol");
        Response response = given()
                .body(requestBody.toString())
                .when()
                .post((String) properties.get("geturl") +"/login")
                .then()
                .statusCode(400)
                .extract().response();
        response.prettyPrint();
    }



    @Test
    @DisplayName("Валидные параметры")
    public void createUser() {
        JSONObject requestBody = new JSONObject()
                .put("name", properties.get("name"))
                .put("job",properties.get("job"));
        Response response = given()
                .body(requestBody.toString())
                .when()
                .post((String) properties.get("geturl") + "/users")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .extract().response();
        response.prettyPrint();

    }

    @DisplayName("Пустые параметры")
    @Test
    public void createUser2() {
        JSONObject requestBody = new JSONObject()
                .put("name", "")
                .put("job", "");
        Response response = given()
                .body(requestBody.toString())
                .when()
                .post((String) properties.get("geturl") + "/users")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .extract().response();
        response.prettyPrint();
    }
    @Test
   public void deleteUser() {
       int code= given()

                .when()
                .delete((String) properties.get("geturl") + "/users?id={id}",2)
                .prettyPeek()
                .then()
               .extract().response().statusCode();
    Assertions.assertEquals(204,204);
    }
}

