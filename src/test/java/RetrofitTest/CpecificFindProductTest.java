package RetrofitTest;


import DTO.Product;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import restApi.CpecificFindProductService;
import retrofit2.Response;
import utils.RetrofitUtils;

import static org.hamcrest.MatcherAssert.assertThat;


public class CpecificFindProductTest {
    static CpecificFindProductService cpecificFindProductService;
    @BeforeAll
    static void beforeAll() {
        cpecificFindProductService= RetrofitUtils.getRetrofit().create(CpecificFindProductService.class);
    }

    @SneakyThrows
    @Test
    void findProductByIdPositiveTest() {
        Response<Product> response = cpecificFindProductService.findProduct2(1).execute();
        System.out.println(response.body());
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
      //  assertThat(response.body().getId(), equalTo(1));
       // assertThat(response.body().getTitle(), equalTo("Electronic"));
     //   response.body().getProducts().forEach(product ->
               // assertThat(product.getCategoryTitle(), equalTo("Electronic")));
    }
}
