package com.example.proj5;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import app.Donut;
import app.Order;
import app.*;

import java.util.ArrayList;
import java.util.Arrays;

public class DonutActivity extends AppCompatActivity {
    private Spinner flavorsList;
    private Spinner quantSpinner;
    private ListView donutsList;
    private Button addDonuts;
    private Button confirmOrder;
    private Order donutOrder;

    ArrayAdapter donutsAdapter;
    private ArrayList<Donut> donuts;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_donut_view);

        flavorsList = findViewById(R.id.flavorList);
        quantSpinner = findViewById(R.id.quantSpinner);

        donutOrder = new Order();
        donuts = new ArrayList();
        donutsList = findViewById(R.id.donutList);
        donutsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, donuts);

        donutsList.setAdapter(donutsAdapter);

        addDonuts = findViewById(R.id.addDonuts);
        addDonuts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderDonut();
            }
        });



        donutsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object listItem = donutsList.getItemAtPosition(position);
                removeDonut(listItem);
            }
        });

        confirmOrder = findViewById(R.id.orderDonuts);
        confirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                orderDonut();
            }
        });
    }

    public void orderDonut(){

        String flavor = (String)flavorsList.getSelectedItem();
        int quantity = Integer.parseInt((String)quantSpinner.getSelectedItem());

        Donut donut = new Donut(quantity,flavor);
        for(Donut d:donuts){
            if(d.getFlavor()==donut.getFlavor()){
                d.setQuantity(d.getQuantity()+quantity);
                donutsAdapter.notifyDataSetChanged();
                return;

            }
        }
        donuts.add(donut);
        donutsAdapter.notifyDataSetChanged();
    }

    public void removeDonut(final Object selected){

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Do you want to remove this item?").setTitle("");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                donuts.remove(selected);
                donutsAdapter.notifyDataSetChanged();

            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    public void orderDonuts(){
        for(Donut d: donuts){
            donutOrder.add(d);
        }
        Intent intent = new Intent();
        intent.putExtra("donutOrder",donutOrder);
        setResult(RESULT_OK,intent);
    }

    public void onStop() {
        super.onStop();
        Intent intent = new  Intent();
    }
}
