package com.example.proj5;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import app.Donut;

import java.util.ArrayList;
import java.util.Arrays;

public class DonutActivity extends AppCompatActivity {
    private Spinner flavorsList;
    private Spinner quantSpinner;
    private ListView donutsList;
    private Button addDonuts;
    ArrayAdapter donutsAdapter;
    private ArrayList<Donut> donuts;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_donut_view);

        flavorsList = findViewById(R.id.flavorList);
        quantSpinner = findViewById(R.id.quantSpinner);

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


    }

    public void orderDonut(){
        String flavor = (String)flavorsList.getSelectedItem();

        int quantity = Integer.parseInt((String)quantSpinner.getSelectedItem());

        Donut donut = new Donut(quantity,flavor);
        donuts.add(donut);
        donutsAdapter.notifyDataSetChanged();
    }
}
