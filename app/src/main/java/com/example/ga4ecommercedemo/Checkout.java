package com.example.ga4ecommercedemo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

public class Checkout extends AppCompatActivity {
    private FirebaseAnalytics analytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Begin Checkout");

        analytics = FirebaseAnalytics.getInstance(this);

        Bundle itemJeggings = new Bundle();
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_ID, "SKU_123");
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_NAME, "jeggings");
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "pants");
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_VARIANT, "black");
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_BRAND, "Google");
        itemJeggings.putDouble(FirebaseAnalytics.Param.PRICE, 9.99);

        Bundle itemJeggingsCart = new Bundle(itemJeggings);
        itemJeggingsCart.putLong(FirebaseAnalytics.Param.QUANTITY, 2);

        Bundle beginCheckoutParams = new Bundle();
        beginCheckoutParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD");
        beginCheckoutParams.putDouble(FirebaseAnalytics.Param.VALUE, 14.98);
        beginCheckoutParams.putString(FirebaseAnalytics.Param.COUPON, "SUMMER_FUN");
        beginCheckoutParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS,
                new Parcelable[]{ itemJeggingsCart });

        analytics.logEvent(FirebaseAnalytics.Event.BEGIN_CHECKOUT, beginCheckoutParams);

        final Button button = findViewById(R.id.btn_add_shipping_info);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle addShippingParams = new Bundle();
                addShippingParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD");
                addShippingParams.putDouble(FirebaseAnalytics.Param.VALUE, 14.98);
                addShippingParams.putString(FirebaseAnalytics.Param.COUPON, "SUMMER_FUN"); //Agrega un descuento
                addShippingParams.putString(FirebaseAnalytics.Param.SHIPPING_TIER, "Ground");
                addShippingParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS,
                        new Parcelable[]{ itemJeggingsCart });

                analytics.logEvent(FirebaseAnalytics.Event.ADD_SHIPPING_INFO, addShippingParams);

                startActivity(new Intent(Checkout.this,Checkout2.class));
            }
        });
    }
}