package com.example.proj5;

import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import app.Donut;
import app.MenuItem;
import app.Order;
import app.StoreOrders;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Order currOrder;
    StoreOrders storeOrders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        currOrder = new Order();
        storeOrders = new StoreOrders();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent donutView = new Intent(this,DonutActivity.class);
        final Button openDonuts = findViewById(R.id.openDonuts);
        openDonuts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(donutView,1);
            }
        });

        final Intent coffeeView = new Intent(this,CoffeeActivity.class);
        final Button openCoffee = findViewById(R.id.openCoffee);
        openCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(coffeeView,2);
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

        currOrder = new Order();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                Order donutOrder = data.getParcelableExtra("donutOrder");
                ArrayList<MenuItem> donutList = donutOrder.getList();
                for(MenuItem d: donutList){
                    currOrder.add(d);
                }
            }
        }else if(requestCode == 2){
            if(resultCode == RESULT_OK) {
                Order coffeeOrder = data.getParcelableExtra("coffeeOrder");
                ArrayList<MenuItem> coffeeList = coffeeOrder.getList();
                for (MenuItem c: coffeeList){
                    currOrder.add(c);
                }
            }
        }
    }

}