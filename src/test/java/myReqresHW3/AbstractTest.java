package myReqresHW3;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;

import io.restassured.http.*;

import io.restassured.specification.*;

import org.junit.jupiter.api.BeforeAll;



import java.io.FileInputStream;
import java.io.IOException;

import java.util.*;

import static org.hamcrest.core.IsEqual.equalTo;

public class AbstractTest {
    static Properties properties = new Properties();
    static ResponseSpecification responseGoodSpecification;
    static ResponseSpecification responseNoGoodSpecification;
    static ResponseSpecification responsePostNoGoodSpecification;
    static RequestSpecification requestSpecification;

    @BeforeAll
    static void setUp() throws IOException {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        FileInputStream fis;
        fis = new FileInputStream("src/test/resources/MyProperties.properties");
        properties.load(fis);
        RestAssured.filters(new AllureRestAssured());


        responseGoodSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .build();


        responseNoGoodSpecification = new ResponseSpecBuilder()
                .expectStatusCode(404)
                .build();

        responsePostNoGoodSpecification = new ResponseSpecBuilder()
                .expectStatusCode(400)
                .expectBody("error", equalTo("Missing email or username"))
                .build();



        requestSpecification = new RequestSpecBuilder()
                .setContentType("application/json; charset=utf-8")
                .build();


    }
}
















