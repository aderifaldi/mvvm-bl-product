package com.ar.mvvmbl.dataservice;

import com.ar.mvvmbl.model.Product;
import com.ar.mvvmbl.model.ProductDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by aderifaldi on 08/08/2016.
 */
public interface ApiService {

    /**
     * END POINT
     */
    String PRODUCT_LIST = "products.json";
    String PRODUCT_DETAIL = "products/{id}.json";

    @GET(PRODUCT_LIST)
    Call<Product> getProducts();

    @GET(PRODUCT_DETAIL)
    Call<ProductDetail> getDetailProduct(@Path("id") String id);

}
