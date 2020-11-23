package com.example.ga4ecommercedemo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Random;

public class Purchase extends AppCompatActivity {
    private FirebaseAnalytics analytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Purchase");

        analytics = FirebaseAnalytics.getInstance(this);

        final int min = 10000;
        final int max = 99999;
        final int random = new Random().nextInt((max - min) + 1) + min;

        TextView textView15 = (TextView)findViewById(R.id.textView15);
        textView15.setText("TRANSACTIONID: T"+random);


        Bundle itemJeggings = new Bundle();
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_ID, "SKU_123");
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_NAME, "jeggings");
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "pants");
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_VARIANT, "black");
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_BRAND, "Google");
        itemJeggings.putDouble(FirebaseAnalytics.Param.PRICE, 9.99);

        Bundle itemJeggingsCart = new Bundle(itemJeggings);
        itemJeggingsCart.putLong(FirebaseAnalytics.Param.QUANTITY, 2);

        Bundle purchaseParams = new Bundle();
        purchaseParams.putString(FirebaseAnalytics.Param.TRANSACTION_ID, "T"+random);
        purchaseParams.putString(FirebaseAnalytics.Param.AFFILIATION, "Google Store");
        purchaseParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD");
        purchaseParams.putDouble(FirebaseAnalytics.Param.VALUE, 14.98);
        purchaseParams.putDouble(FirebaseAnalytics.Param.TAX, 2.58);
        purchaseParams.putDouble(FirebaseAnalytics.Param.SHIPPING, 5.34);
        purchaseParams.putString(FirebaseAnalytics.Param.COUPON, "SUMMER_FUN");
        purchaseParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS,
                new Parcelable[]{ itemJeggingsCart });

        analytics.logEvent(FirebaseAnalytics.Event.PURCHASE, purchaseParams);

    }
}