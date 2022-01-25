package myReqresHW3;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class AbstractTest {
    static Properties properties = new Properties();


    @BeforeAll
    static void setUp() throws IOException {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        FileInputStream fis;
        fis = new FileInputStream("src/test/resources/MyProperties.properties");
        properties.load(fis);
        RestAssured.filters(new AllureRestAssured());

    }
}
