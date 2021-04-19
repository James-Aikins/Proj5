package com.example.proj5;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent donutView = new Intent(this,DonutActivity.class);
        final Button openDonuts = findViewById(R.id.openDonuts);
        openDonuts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(donutView);
            }
        });
        final Intent coffeeView = new Intent(this,CoffeeActivity.class);
        final Button openCoffee = findViewById(R.id.openCoffee);
        openCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(coffeeView);
            }
        });

        final Intent currView = new Intent(this,CurrOrderActivity.class);
        final Button openCurrOrder = findViewById(R.id.openCurr);
        openCurrOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(currView);
            }
        });

        final Intent storeOrderView = new Intent(this,StoreOrderActivity.class);
        final Button openStoreOrder = findViewById(R.id.openStoreOrder);
        openStoreOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(storeOrderView);
            }
        });
    }
}