package restApi;


import DTO.Product;
import retrofit2.Call;

import retrofit2.http.POST;

import retrofit2.http.Query;

public interface CpecificFindProductService {
    @POST("products/{id}")
    Call<Product> findProduct2 (@Query ("id") int id);
    //(@Path("id") int id);
}
