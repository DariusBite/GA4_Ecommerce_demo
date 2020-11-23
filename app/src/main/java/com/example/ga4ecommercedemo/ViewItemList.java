package com.example.ga4ecommercedemo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;


public class ViewItemList extends AppCompatActivity {
    private FirebaseAnalytics analytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("ViewL Item List");

        analytics = FirebaseAnalytics.getInstance(this);

        Bundle itemJeggings = new Bundle();
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_ID, "SKU_123");
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_NAME, "jeggings");
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "pants");
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_VARIANT, "black");
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_BRAND, "Google");
        itemJeggings.putDouble(FirebaseAnalytics.Param.PRICE, 9.99);

        Bundle itemJeggingsWithIndex = new Bundle(itemJeggings);
        itemJeggingsWithIndex.putLong(FirebaseAnalytics.Param.INDEX, 1);

        Bundle viewItemListParams = new Bundle();
        viewItemListParams.putString(FirebaseAnalytics.Param.ITEM_LIST_ID, "L001");
        viewItemListParams.putString(FirebaseAnalytics.Param.ITEM_LIST_NAME, "Related products");
        viewItemListParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS,
                new Parcelable[]{itemJeggingsWithIndex});
        analytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM_LIST, viewItemListParams);


        final Button button = findViewById(R.id.btn_select_item);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle selectItemParams = new Bundle();
                selectItemParams.putString(FirebaseAnalytics.Param.ITEM_LIST_ID, "L001");
                selectItemParams.putString(FirebaseAnalytics.Param.ITEM_LIST_NAME, "Related products");
                selectItemParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS,
                        new Parcelable[]{ itemJeggings });
                analytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM, selectItemParams);


                startActivity(new Intent(ViewItemList.this,ViewProductDetails.class));

            }
        });

    }
}