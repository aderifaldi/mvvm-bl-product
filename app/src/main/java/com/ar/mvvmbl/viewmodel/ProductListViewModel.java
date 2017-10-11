package com.ar.mvvmbl.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;

import com.ar.mvvmbl.model.Product;
import com.bumptech.glide.Glide;
import com.radyalabs.apputility.AppUtility;

/**
 * Created by Irfan AFif on 10/6/2017.
 */

public class ProductListViewModel extends BaseObservable {

    private View.OnClickListener onClickListener;
    private String productName;
    private String productPrice;
    private String productDesc;
    private String productImage;

    public ProductListViewModel(Product.Products product, View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;

        productName = product.getName();
        productPrice = AppUtility.formatMoney("Rp. ", product.getPrice(), '.', "");
        productDesc = String.valueOf(Html.fromHtml(product.getDesc()));
        productImage = product.getImages().get(0);

    }

    public String getProductName() {
        return productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public String getProductImage() {
        return productImage;
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public void onItemClick(View view) {
        onClickListener.onClick(view);
    }

}
