package restApi;


import DTO.Product;

import retrofit2.Call;
import retrofit2.http.*;



public interface FindProductsService {
    @GET("products")
    Call<Product> findProduct (@Query ("categoryTitle") String param);
}
