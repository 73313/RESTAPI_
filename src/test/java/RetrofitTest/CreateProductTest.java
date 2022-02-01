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
import restApi.ProductService;
import retrofit2.Response;
import utils.RetrofitUtils;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class CreateProductTest {
    static ProductService productService;
    Product product;
    Faker faker = new Faker();

    int id;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);

    }
    @BeforeEach
    void setUp() {
         product = new Product()

       // .withTitle(faker.food().ingredient())
                 .withTitle("Soda")
               .withCategoryTitle("Food")
               .withPrice((int) (Math.random() * 10000));


    }
    @Test
    @SneakyThrows
    void createProductInFoodCategoryTest() {
        Response<Product> response = productService.createProduct(product)
                .execute();
        id =  response.body().getId();
        System.out.println(id);
        System.out.println(response.body().getCategoryTitle());
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        System.out.println(response.body());
        assertThat(response.body().getTitle(), equalTo("Soda"));
    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }





}