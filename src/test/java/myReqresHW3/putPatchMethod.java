package myReqresHW3;


import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class putPatchMethod extends AbstractTest {

    @Test
    public void updateUser() {
        given()
                .param("name", "Ekaterina")
                .param("job", "Disayner")
                .when()
                .put((String) properties.get("geturl") + "/users?id={id}", 8)
                .prettyPeek()
                .then()
                .statusCode(200);


    }
    @Test
    public void updateUser2() {
        given()
                .param("job", "Disayner")
                .when()
                .put((String) properties.get("geturl") + "/users?id={id}", 12)
                .then()
                .statusCode(200)
                .log()
                .body();

    }
    @DisplayName("Незаполненные данные")
    @Test
    public void updateUser3() {
        given()
                .param("job", "")
                .when()
                .put((String) properties.get("geturl") + "/users?id={id}", 12)
                .then()
                .statusCode(200)
                .log()
                .body();

    }
    @Test
    public void updateUser4() {
        given()
                .param("name", "Ekaterina")
                .param("job", "Disayner")
                .when()
                .patch((String) properties.get("geturl") + "/users?id={id}", 5)
                .then()
                .statusCode(200)
                .log()
                .body();
    }
@DisplayName("Невалидные данные")
    @Test
    public void updateUser5() {
        String str =given()

                .param("job", "@@@@@")
                .when()
                .patch((String) properties.get("geturl") + "/users?id={id}", 5)
                .then()
                .statusCode(200)
                .extract().response().jsonPath().getString("updatedAt");
    System.out.println(str);

    }
}
