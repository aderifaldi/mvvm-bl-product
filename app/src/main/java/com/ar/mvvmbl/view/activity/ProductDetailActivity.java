package com.ar.mvvmbl.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.ar.mvvmbl.R;
import com.ar.mvvmbl.databinding.ProductDetailActivityBinding;
import com.ar.mvvmbl.dataservice.ApiService;
import com.ar.mvvmbl.dataservice.ServiceGenerator;
import com.ar.mvvmbl.model.Product;
import com.ar.mvvmbl.model.ProductDetail;
import com.ar.mvvmbl.viewmodel.ProductDetailViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private ProductDetailActivityBinding productDetailActivityBinding;
    private Product.Products product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productDetailActivityBinding = DataBindingUtil.setContentView(this, R.layout.product_detail_activity);
        ButterKnife.bind(this);

        loadProductDetail();

    }

    private void loadProductDetail() {

        product = (Product.Products) getIntent().getExtras().getSerializable("data");

        if (product != null){
            ApiService apiService = ServiceGenerator.createService(ApiService.class);
            Call<ProductDetail> call = apiService.getDetailProduct(product.getId());
            call.enqueue(new Callback<ProductDetail>() {
                @Override
                public void onResponse(Call<ProductDetail> call, Response<ProductDetail> response) {
                    progressBar.setVisibility(View.GONE);

                    if (response.isSuccessful() && response.body().getStatus().equals("OK")){
                        if (response.body().getProduct() != null){

                            ProductDetail.Product data = response.body().getProduct();

                            ProductDetailViewModel productDetailViewModel = new ProductDetailViewModel(data);
                            productDetailActivityBinding.setProductDetailData(productDetailViewModel);

                        }
                    }

                }

                @Override
                public void onFailure(Call<ProductDetail> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                }
            });
        }

    }
}
