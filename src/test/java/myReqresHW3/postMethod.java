package myReqresHW3;



import Pojo.MyDTO;
import Pojo.MyDTO2;
import Pojo.UserCreateDTO;
import io.restassured.http.ContentType;

import io.restassured.response.Response;

import org.apache.tapestry5.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class postMethod extends AbstractTest {





    @Test
    public void REGISTER1() {
        String token = given(requestSpecification).log().all()

                .when().body("{\n" +
                        " \"email\":\"eve.holt@reqres.in\",\n" +
                        " \"password\": \"pistol\"\n" +
                        "}")
                .post((String) properties.get("geturl") +"/register")
                .prettyPeek()
                .then()
                .spec(responseGoodSpecification)
                .body("id", equalTo(4))
                .extract()
                .jsonPath()
                .getString("token");
        System.out.println(token);

    }

    @Test
    public void registerPojo() {
        MyDTO myDTO =new MyDTO();
        myDTO.setEmail("eve.holt@reqres.in");
        myDTO.setPassword("pistol");

        String token = given(requestSpecification).log().all()

                .when().body(myDTO)
                .post((String) properties.get("geturl") +"/register")
                .prettyPeek()
                .then()
                .spec(responseGoodSpecification)
                .body("id", equalTo(4))
                .extract()
                .jsonPath()
                .getString("token");
        System.out.println(token);

    }


    @DisplayName("без пароля")
    @Test
    public void REGISTER2() {
        given(requestSpecification).log().all()
                .when().body("{\n" +
                        " \"email\":\"eve.holt@reqres.in\",\n" +
                        "}")
                .post((String) properties.get("geturl") +"/register")
                .prettyPeek()
                .then()
                .statusCode(400);

    }
    @DisplayName("без пароля")
    @Test
    public void registerPojo2() {
        MyDTO myDTO =new MyDTO();
        myDTO.setEmail("eve.holt@reqres.in");
        given(requestSpecification).log().all()
                .when().body(myDTO)
                .post((String) properties.get("geturl") +"/register")
                .prettyPeek()
                .then()
                .statusCode(400);

    }


    @DisplayName("Невалидный email")
    @Test
    public void REGISTER3() {

        given(requestSpecification).log().all()
                .when().body("{\n" +
                        " \"email\":\"@@@@@@\",\n" +
                        " \"password\": \"pistol\"\n" +
                        "}")
                .post((String) properties.get("geturl") +"/register")
                .prettyPeek()
                .then()
                .statusCode(400);

    }

    @DisplayName("Невалидный email")
    @Test
    public void registerPojo3() {
        MyDTO myDTO =new MyDTO();
        myDTO.setEmail("@@@@@@");
        myDTO.setPassword("pistol");
        given(requestSpecification).log().all()
                .when().body(myDTO)
                .post((String) properties.get("geturl") +"/register")
                .prettyPeek()
                .then()
                .statusCode(400);

    }

    @DisplayName("пустой email и пустой password")
    @Test
    public void REGISTER4() {
        given(requestSpecification).log().all()
                .when().body("{\n" +
                        " \"email\":\"\",\n" +
                        " \"password\": \"\"\n" +
                        "}")
                .post((String) properties.get("geturl") +"/register")
                .prettyPeek()
                .then()
                .spec(responsePostNoGoodSpecification);

    }

    @DisplayName("пустой email и пустой password")
    @Test
    public void registerPojo4() {
        MyDTO myDTO =new MyDTO();
        myDTO.setEmail("");
        myDTO.setPassword("");
        given(requestSpecification).log().all()
                .when().body(myDTO)
                .post((String) properties.get("geturl") +"/register")
                .prettyPeek()
                .then()
                .statusCode(400);

    }
    @Test
    public void LOGIN1() {
        String token = given(requestSpecification).log().all()

                .when().body("{\n" +
                        " \"email\":\"eve.holt@reqres.in\",\n" +
                        " \"password\": \"pistol\"\n" +
                        "}")
                .post((String) properties.get("geturl") +"/login")
                .prettyPeek()
                .then()
                .spec(responseGoodSpecification)
                .extract()
                .jsonPath()
                .getString("token");
    }

    @DisplayName("без пароля")
    @Test
    public void LOGIN2() {
        given(requestSpecification).log().all()
                .when().body("{\n" +
                        " \"email\":\"eve.holt@reqres.in\",\n" +
                        "}")
                .post((String) properties.get("geturl") +"/register")
                .prettyPeek()
                .then()
                .statusCode(400);

    }

    @DisplayName("Невалидный email")
    @Test
    public void LOGIN3() {
        given(requestSpecification).contentType("application/json; charset=utf-8").log().all()
                .when().body("{\n" +
                        " \"email\":\"@@@@@@\",\n" +
                        " \"password\": \"pistol\"\n" +
                        "}")
                .post((String) properties.get("geturl") +"/register")
                .prettyPeek()
                .then()
                .statusCode(400);

    }

    @DisplayName("пустой email и пустой password")
    @Test
    public void LOGIN4() {
        given(requestSpecification).log().all()
                .when().body("{\n" +
                        " \"email\":\"\",\n" +
                        " \"password\": \"\"\n" +
                        "}")
                .post((String) properties.get("geturl") +"/register")
                .prettyPeek()
                .then()
                .spec(responsePostNoGoodSpecification);

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
                .extract().response();
        response.prettyPrint();

    }
    @Test
    @DisplayName("Валидные параметры")
    public void createUserPojo() {
        MyDTO2 myDTO =new MyDTO2();
        myDTO.setName("Sveta");
        myDTO.setJob("admin");
        UserCreateDTO user=given(requestSpecification)
                .when().body(myDTO)
                .post((String) properties.get("geturl") + "/users")
                .prettyPeek()
                .then()
                .statusCode(201).extract().body()
                .as(UserCreateDTO.class);
        assertThat(user.getName(),equalTo("Sveta") );

        System.out.println(user.getJob()+": "+ " "+user.getName());

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
        System.out.println(code);
    Assertions.assertEquals(204,204);
    }
}

