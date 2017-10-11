package com.ar.mvvmbl.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ar.mvvmbl.BR;
import com.ar.mvvmbl.R;
import com.ar.mvvmbl.helper.GenericViewHolder;
import com.ar.mvvmbl.model.Product;
import com.ar.mvvmbl.view.activity.ProductDetailActivity;
import com.ar.mvvmbl.viewmodel.ProductListViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RadyaLabs PC on 11/10/2017.
 */

public class ProductListAdapter extends RecyclerView.Adapter<GenericViewHolder> {
    private List<Product.Products> listItem;
    private Context context;

    public ProductListAdapter(Context context) {
        this.listItem = new ArrayList<>();
        this.context = context;
    }

    public List<Product.Products> getListItem() {
        return listItem;
    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.product_item, parent, false);
        GenericViewHolder vh = new GenericViewHolder(binding);
        return vh;
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {
        final Product.Products itemData = listItem.get(position);
        ProductListViewModel viewModel = new ProductListViewModel(itemData, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ProductDetailActivity.class)
                        .putExtra("data", itemData));
            }
        });

        holder.bindModel(BR.productData, viewModel);
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }
}
