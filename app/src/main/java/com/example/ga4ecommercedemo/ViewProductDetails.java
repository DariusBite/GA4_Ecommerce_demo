package com.example.ga4ecommercedemo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

public class ViewProductDetails extends AppCompatActivity {
    private FirebaseAnalytics analytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product_details);
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("View Product Detail");

        analytics = FirebaseAnalytics.getInstance(this);

        Bundle itemJeggings = new Bundle();
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_ID, "SKU_123");
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_NAME, "jeggings");
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "pants");
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_VARIANT, "black");
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_BRAND, "Google");
        itemJeggings.putDouble(FirebaseAnalytics.Param.PRICE, 9.99);

        Bundle viewItemParams = new Bundle();
        viewItemParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD");
        viewItemParams.putDouble(FirebaseAnalytics.Param.VALUE, 9.99);
        viewItemParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS,
                new Parcelable[] { itemJeggings });

        analytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, viewItemParams);

        final Button button = findViewById(R.id.btn_add_to_cart);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle itemJeggingsCart = new Bundle(itemJeggings);
                itemJeggingsCart.putLong(FirebaseAnalytics.Param.QUANTITY, 2);

                Bundle addToCartParams = new Bundle();
                addToCartParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD");
                addToCartParams.putDouble(FirebaseAnalytics.Param.VALUE, 2 * 9.99); // QUANTITY * Jeggings price
                addToCartParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS,
                        new Parcelable[]{ itemJeggingsCart });

                analytics.logEvent(FirebaseAnalytics.Event.ADD_TO_CART, addToCartParams);

                startActivity(new Intent(ViewProductDetails.this,ViewCart.class));
            }
        });

    }
}