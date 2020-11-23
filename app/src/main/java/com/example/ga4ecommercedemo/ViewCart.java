package com.example.ga4ecommercedemo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

public class ViewCart extends AppCompatActivity {
    private FirebaseAnalytics analytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("View Cart");

        analytics = FirebaseAnalytics.getInstance(this);

        Bundle itemJeggings = new Bundle();
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_ID, "SKU_123");
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_NAME, "jeggings");
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "pants");
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_VARIANT, "black");
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_BRAND, "Google");
        itemJeggings.putDouble(FirebaseAnalytics.Param.PRICE, 9.99);

        Bundle itemBoots = new Bundle();
        itemBoots.putString(FirebaseAnalytics.Param.ITEM_ID, "SKU_456");
        itemBoots.putString(FirebaseAnalytics.Param.ITEM_NAME, "boots");
        itemBoots.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "shoes");
        itemBoots.putString(FirebaseAnalytics.Param.ITEM_VARIANT, "brown");
        itemBoots.putString(FirebaseAnalytics.Param.ITEM_BRAND, "Google");
        itemBoots.putDouble(FirebaseAnalytics.Param.PRICE, 24.99);

        Bundle itemJeggingsCart = new Bundle(itemJeggings);
        itemJeggingsCart.putLong(FirebaseAnalytics.Param.QUANTITY, 2);

        Bundle itemBootsCart = new Bundle(itemBoots);
        itemBootsCart.putLong(FirebaseAnalytics.Param.QUANTITY, 1);

        Bundle viewCartParams = new Bundle();
        viewCartParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD");
        viewCartParams.putDouble(FirebaseAnalytics.Param.VALUE, (2 * 9.99) + (1 * 24.99));// Jeggings QUANTITY * Jeggings price + boots QUANTITY * boots price
        viewCartParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS,
                new Parcelable[]{ itemJeggingsCart, itemBootsCart });

        analytics.logEvent(FirebaseAnalytics.Event.VIEW_CART, viewCartParams);


        final Button button = findViewById(R.id.btn_begin_checkout);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle removeCartParams = new Bundle();
                removeCartParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD");
                removeCartParams.putDouble(FirebaseAnalytics.Param.VALUE, (1 * 24.99));
                removeCartParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS,
                        new Parcelable[]{ itemBootsCart });

                analytics.logEvent(FirebaseAnalytics.Event.REMOVE_FROM_CART, removeCartParams);

                startActivity(new Intent(ViewCart.this,Checkout.class));
            }
        });

    }
}