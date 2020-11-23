package com.example.ga4ecommercedemo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

public class Checkout2 extends AppCompatActivity {
    private FirebaseAnalytics analytics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout2);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Checkout 2");

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

        final Button button = findViewById(R.id.btn_add_payment_info);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle addPaymentParams = new Bundle();
                addPaymentParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD");
                addPaymentParams.putDouble(FirebaseAnalytics.Param.VALUE, 14.98);
                addPaymentParams.putString(FirebaseAnalytics.Param.COUPON, "SUMMER_FUN");
                addPaymentParams.putString(FirebaseAnalytics.Param.PAYMENT_TYPE, "Visa");
                addPaymentParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS,
                        new Parcelable[]{ itemJeggingsCart });

                analytics.logEvent(FirebaseAnalytics.Event.ADD_PAYMENT_INFO, addPaymentParams);

                startActivity(new Intent(Checkout2.this,Checkout3.class));
            }
        });
    }
}