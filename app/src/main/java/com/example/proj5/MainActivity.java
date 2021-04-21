package com.example.proj5;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import app.*;




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
                currView.putExtra("currOrder",currOrder);
                startActivityForResult(currView,3);
            }
        });

        final Intent storeOrderView = new Intent(this,StoreOrderActivity.class);
        final Button openStoreOrder = findViewById(R.id.openStoreOrder);
        openStoreOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeOrderView.putExtra("storeOrders",storeOrders);
                startActivity(storeOrderView);
            }
        });

        currOrder = new Order();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                Order donutOrder = (Order)data.getSerializableExtra("donutOrder");
                for(MenuItem d: donutOrder.getList()){
                    currOrder.add(d);
                }
            }
        }else if(requestCode == 2){
            if(resultCode == RESULT_OK) {
                Order coffeeOrder = (Order)data.getSerializableExtra("coffeeOrder");
                for (MenuItem c: coffeeOrder.getList()){
                    currOrder.add(c);
                }
            }
        }else if(requestCode == 3){
            if(resultCode == RESULT_OK){
                Order placedOrder = (Order)data.getSerializableExtra("order");
                storeOrders.add(placedOrder);
            }
        }
    }

}