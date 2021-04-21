package com.example.proj5;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import app.MenuItem;
import app.Order;
import app.StoreOrders;
import app.values;

import java.io.Serializable;
import java.util.ArrayList;

public class StoreOrderActivity extends AppCompatActivity implements Serializable {

    private ListView orderList;
    private ListView orderItems;
    private TextView orderNumber;
    private TextView subtotal;
    private StoreOrders storeOrders;
    private Button removeOrder;

    private ArrayAdapter adapter;
    private ArrayAdapter orderAdapter;
    private ArrayList<MenuItem> itemList;
    private Order selected;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_order);

        Intent intent = getIntent();
        storeOrders = (StoreOrders)intent.getSerializableExtra("storeOrders");

        orderNumber = findViewById(R.id.orderNumber);
        subtotal = findViewById(R.id.subtotalStoreOrder);

        removeOrder = findViewById(R.id.removeOrder);
        removeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeOrder(selected);

            }
        });

        orderList = findViewById(R.id.storeOrderList);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,storeOrders.getList());
        orderList.setAdapter(adapter);
        orderItems = findViewById(R.id.orderItems);

        orderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected = storeOrders.getList().get(position);
                selectOrder(selected);
            }
        });



    }

    private void selectOrder(Order selected){
        itemList = selected.getList();
        orderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,itemList);
        orderItems.setAdapter(orderAdapter);

        orderNumber.setText(String.valueOf(selected.getOrderNumber()));
        double total = selected.totalPrice() + (values.TAXRATE*selected.totalPrice());
        subtotal.setText(String.format("%,.2f",total));



    }
    private void removeOrder(final Order selected){

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Do you want to remove this item?").setTitle("");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                storeOrders.remove(selected);
                adapter.notifyDataSetChanged();
                itemList = new ArrayList<>();
                orderAdapter.notifyDataSetChanged();
                orderNumber.setText(R.string.autofill);
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

}
