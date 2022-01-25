package myReqresHW3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.core.IsEqual.equalTo;

public class getMethod extends AbstractTest {

    @Test
    void getAllUsers() {
       given()
                .when()
                .get((String) properties.get("geturl") + "/users?page={page}", 2)
                .then()
               .body("page", equalTo(2))
               .statusCode(200);
    }

    @Test
    public void getSingleUser() {
     String str  = given()
                .when()
                .get((String) properties.get("geturl") + "/users?id={id}", 12)
                .prettyPeek()
                .then()
                //.contentType(ContentType.JSON)
                .body("data.first_name", equalTo("Rachel"))
                .body("data.avatar", equalTo("https://reqres.in/img/faces/12-image.jpg"))
                .statusCode(200).extract().response().jsonPath().getString("data.last_name");
        System.out.println(str);
       // assertThat("Howell", equalTo("Howell"));


    }

    @Test
    public void getParamUser() {
        given()
                .when()
                .get((String) properties.get("geturl") + "/users?id={id}", 7)
                .then()
                .contentType(ContentType.JSON)
                .body("data.first_name", equalTo("Michael"))
                .statusCode(200);


    }

    @Test
    public void getParamUser2() {
        String str = given()
                .when()
                .get((String) properties.get("geturl") + "/users?id={id}", 8)
                .then()
                .statusCode(200)
                .log()
                .body()
                .body("data.first_name", equalTo("Lindsay"))
                .body("data.email", equalTo("lindsay.ferguson@reqres.in"))
                .extract()
                .response()
                .jsonPath()
                .getString("data.avatar");
        System.out.println(str);
    }


    @Test
    public void findUser() {
        given()
                .when()
                .get((String) properties.get("geturl") + "/unknown?id={id}", 2)
                .then()
                .statusCode(200)
                .log()
                .body()
                .body("data.id",equalTo(2));
        assertThat("https://reqres.in/api//unknown?id=2", equalTo("https://reqres.in/api//unknown?id=2"));
    }

    @DisplayName("пользователя не существует")
    @Test
    public void findNoUser() {
        given()
                .when()
                .get((String) properties.get("geturl") + "/unknown?id={id}", 44444444)
                .prettyPeek()
                .then()
                 .statusCode(404);


    }
    @DisplayName("пользователя не существует по имени")
    @Test

    public void findNoUser1() {
        given()
                .log()
                .method()
                .when()
                .request("GET","https://reqres.in/api/users?id={id}", "Janet")
                .then()
                .statusCode(404);



    }

    @Test
    public void findImage (){

        RestAssured.get("https://reqres.in/img/faces/7-image.jpg")
                .then()
                .statusCode(200);
    }

}

