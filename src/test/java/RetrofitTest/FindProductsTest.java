package RetrofitTest;

import DTO.Product;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import restApi.FindProductsService;
import retrofit2.Response;
import utils.RetrofitUtils;

import static org.hamcrest.MatcherAssert.assertThat;

public class FindProductsTest {
    static FindProductsService findcategoryService;
    //Product product;


    @BeforeAll
    static void beforeAll() {
        findcategoryService = RetrofitUtils.getRetrofit().create(FindProductsService.class);
    }

   /* @BeforeEach
    void setUp() {
        product = new Product()
                .withId(1)
                // .withTitle(faker.food().ingredient())
                .withTitle("Bread")
                .withCategoryTitle("Food")
                .withPrice(100);

    }*/

    @Test
    @SneakyThrows
    void findProductInFoodCategoryTest() {
        Response<Product> response = findcategoryService.findProduct("Food")
               .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}
