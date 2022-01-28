package myReqresHW3;

import Pojo.MyDTO2;
import Pojo.UserUpdateDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class putPatchMethod extends AbstractTest {

    @Test
    public void updateUser() {
        given(requestSpecification)
                .param("name", "Ekaterina")
                .param("job", "Disayner")
                .when()
                .put((String) properties.get("geturl") + "/users?id={id}", 8)
                .prettyPeek()
                .then()
                .spec(responseGoodSpecification);
    }
    @Test
    public void updateUserPojo() {
        MyDTO2 myDTO =new MyDTO2();
        myDTO.setName("Ekaterina");
        myDTO.setJob("Disayner");
        UserUpdateDTO user=given(requestSpecification)
                .when().body(myDTO)
                .put((String) properties.get("geturl") + "/users?id={id}", 8)
                .prettyPeek()
                .then()
                .spec(responseGoodSpecification)
                .extract().body()
                .as(UserUpdateDTO.class);
        System.out.println(user.getJob());
    }

    @Test
    public void updateUser2() {
        given()
                .param("job", "Disayner")
                .when()
                .put((String) properties.get("geturl") + "/users?id={id}", 12)
                .then()
                .spec(responseGoodSpecification)
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
                .spec(responseGoodSpecification)
                .log()
                .body();

    }
    @Test
    public void updateUser4() {
        given(requestSpecification)
                .param("name", "Ekaterina")
                .param("job", "Disayner")
                .when()
                .patch((String) properties.get("geturl") + "/users?id={id}", 5)
                .then()
                .spec(responseGoodSpecification)
                .log()
                .body();
    }
@DisplayName("Невалидные данные")
    @Test
    public void updateUser5() {
        String str =given(requestSpecification)

                .param("job", "@@@@@")
                .when()
                .patch((String) properties.get("geturl") + "/users?id={id}", 5)
                .then()
                .spec(responseGoodSpecification)
                .extract().response().jsonPath().getString("updatedAt");
    System.out.println(str);

    }
}
