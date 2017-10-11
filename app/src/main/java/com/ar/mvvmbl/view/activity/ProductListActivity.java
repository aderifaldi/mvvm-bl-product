package com.ar.mvvmbl.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.ar.mvvmbl.R;
import com.ar.mvvmbl.dataservice.ApiService;
import com.ar.mvvmbl.dataservice.ServiceGenerator;
import com.ar.mvvmbl.model.Product;
import com.ar.mvvmbl.view.adapter.ProductListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListActivity extends AppCompatActivity {

    @BindView(R.id.listProduct)
    RecyclerView listProduct;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private LinearLayoutManager linearLayoutManager;
    private ProductListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list_activity);
        ButterKnife.bind(this);

        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new ProductListAdapter(this);

        listProduct.setAdapter(adapter);
        listProduct.setLayoutManager(linearLayoutManager);

        loadProduct();

    }

    private void loadProduct() {
        ApiService apiService = ServiceGenerator.createService(ApiService.class);
        Call<Product> call = apiService.getProducts();
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body().getStatus().equals("OK")) {
                    Product data = response.body();
                    if (data.getProducts() != null && data.getProducts().size() != 0) {
                        adapter.getListItem().clear();

                        for (int i = 0; i < data.getProducts().size(); i++) {
                            adapter.getListItem().add(data.getProducts().get(i));
                            adapter.notifyItemInserted(adapter.getListItem().size() - 1);
                        }

                        adapter.notifyDataSetChanged();

                    }
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });

    }
}
