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
import java.text.DecimalFormat;

import java.util.ArrayList;


public class DonutActivity extends AppCompatActivity {
    private Spinner flavorsList;
    private Spinner quantSpinner;
    private ListView donutsList;
    private Button addDonuts;
    private Button confirmOrder;
    private Order donutOrder;
    private TextView subtotalNum;
    private static DecimalFormat df = new DecimalFormat("#.##");

    ArrayAdapter donutsAdapter;
    private ArrayList<Donut> donuts;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_donut_view);

        flavorsList = findViewById(R.id.flavorList);
        quantSpinner = findViewById(R.id.quantSpinner);
        subtotalNum = findViewById(R.id.donutSubtotal);
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
                confirmsDonuts();
            }
        });
    }

    public void orderDonut(){

        String flavor = (String)flavorsList.getSelectedItem();
        int quantity = Integer.parseInt((String)quantSpinner.getSelectedItem());
        double newSubtotal;
        Donut donut = new Donut(quantity,flavor);
        for(Donut d:donuts){
            if(d.getFlavor()==donut.getFlavor()){
                d.setQuantity(d.getQuantity()+quantity);
                newSubtotal = (Double.parseDouble((String) subtotalNum.getText())) + (donut.getPrice()*quantity);
                subtotalNum.setText(df.format(newSubtotal));
                donutsAdapter.notifyDataSetChanged();
                return;

            }
        }
        donuts.add(donut);
        newSubtotal = (Double.parseDouble((String) subtotalNum.getText())) + (donut.getPrice()*quantity);
        subtotalNum.setText(df.format(newSubtotal));
        donutsAdapter.notifyDataSetChanged();
    }

    public void removeDonut(final Object selected){
        final Donut newDonut = (Donut) selected;
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Do you want to remove this item?").setTitle("");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                donuts.remove(selected);
                double newSubtotal = (Double.parseDouble((String) subtotalNum.getText())) - (newDonut.getPrice()*newDonut.getQuantity());
                subtotalNum.setText(df.format(newSubtotal));
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

    public void confirmsDonuts(){
        for(Donut d: donuts){
            donutOrder.add(d);
        }
        Intent intent = new Intent();
        intent.putExtra("donutOrder",donutOrder);
        setResult(RESULT_OK,intent);
    }

}
