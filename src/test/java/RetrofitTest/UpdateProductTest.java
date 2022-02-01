package RetrofitTest;

import DTO.Product;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restApi.UpdateProductService;
import retrofit2.Response;
import utils.RetrofitUtils;

import static org.hamcrest.MatcherAssert.assertThat;

public class UpdateProductTest {
    static UpdateProductService updateProductService;
    Product product;

    Faker faker = new Faker();

    //int id;

    @BeforeAll
    static void beforeAll() {
        updateProductService = RetrofitUtils.getRetrofit()
                .create(UpdateProductService.class);

    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withId(1)
                .withTitle("Bread2")
                .withPrice(100)
                .withCategoryTitle("Food");


    }

    @Test
    @SneakyThrows
    void createProductInFoodCategoryTest() {
        Response<Product> response = updateProductService.updateProduct(product)
                .execute();
        System.out.println(response.body());
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        // System.out.println(response.body());
        //assertThat(response.body().getTitle(), equalTo("Bread2"));
    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        Response<ResponseBody> response =updateProductService.deleteProduct(1).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));


    }
}
