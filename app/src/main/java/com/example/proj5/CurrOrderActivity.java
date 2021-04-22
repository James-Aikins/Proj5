package com.example.proj5;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import app.*;

/**
 * Controller for the Current Order page
 * @author James Aikins, Michael Radoian
 */
public class CurrOrderActivity extends AppCompatActivity {
    private ListView orderList;
    private TextView subtotal;
    private TextView tax;
    private TextView total;

    private Order currOrder;

    double subTotalPrice = 0;
    double taxPrice = 0;
    double totalPrice = 0;

    ArrayAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_order);
        Intent intent = getIntent();
        currOrder = (Order)intent.getSerializableExtra("currOrder");

        subtotal = findViewById(R.id.subtotalNumber);
        tax = findViewById(R.id.taxNumber);
        total = findViewById(R.id.totalNumber);

        Button placeOrder = findViewById(R.id.placeOrder);
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmOrder();
            }
        });

        orderList = findViewById(R.id.orderList);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,currOrder.getList());
        orderList.setAdapter(adapter);



        orderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object listItem = orderList.getItemAtPosition(position);
                removeItem(listItem);
                adapter.notifyDataSetChanged();
            }
        });


        updateCost();

    }

    /**
     * Recalculates the cost values and updates the subtotal tax and total TextFields with the new data
     */
    public void updateCost(){
        subTotalPrice = currOrder.totalPrice();
        taxPrice = subTotalPrice * values.TAXRATE;
        totalPrice = subTotalPrice + taxPrice;

        subtotal.setText(String.format("%,.2f",subTotalPrice));
        tax.setText(String.format("%,.2f",taxPrice));
        total.setText(String.format(("%,.2f"),totalPrice));
    }

    /**
     * Removes the selected item from the list of menu items
     * @param selected The item
     */
    public void removeItem(final Object selected){

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Do you want to remove this item?").setTitle("");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                currOrder.remove(selected);
                adapter.notifyDataSetChanged();
                updateCost();

            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    /**
     * Puts the confirmed order onto the activity intent
     */
    public void confirmOrder(){
        Context context = getApplicationContext();
        CharSequence text;
        Toast toast;
        int duration = Toast.LENGTH_SHORT;

        if(currOrder.isEmpty()){
            text = "Please add items before ordering";
            toast = Toast.makeText(context,text,duration);
            toast.show();
            return;
        }

        currOrder.clear();
        subtotal.setText("0.00");
        total.setText("0.00");
        tax.setText("0.00");
        adapter.notifyDataSetChanged();

        text = "You have ordered successfully";
        toast = Toast.makeText(context,text,duration);
        toast.show();
        Intent intent = new Intent();
        intent.putExtra("order",currOrder);
        setResult(RESULT_OK,intent);
    }
}
