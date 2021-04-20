package com.example.proj5;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import app.Coffee;
import java.util.ArrayList;
import static app.values.*;
import java.text.DecimalFormat;


public class CoffeeActivity extends AppCompatActivity {

    private Spinner sizeSpinner;
    private Spinner numCoffees;
    private Button addCoffee;
    private Button orderCoffee;
    private TextView subtotalNum;
    private ListView coffeeList;
    private CheckBox cream;
    private CheckBox sugar;
    private CheckBox whippedCream;
    private static DecimalFormat df = new DecimalFormat("#.##");


    ArrayAdapter coffeesAdapter;
    private ArrayList<Coffee> coffees;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coffee_order);

        sizeSpinner = findViewById(R.id.sizeSpinner);
        numCoffees = findViewById(R.id.numCoffees);

        subtotalNum = findViewById(R.id.subtotalNum);

        coffees = new ArrayList();
        coffeeList = findViewById(R.id.coffeeList);
        coffeesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,coffees);

        coffeeList.setAdapter(coffeesAdapter);

        addCoffee = findViewById(R.id.addCoffee);
        addCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderCoffee();
            }
        });

        cream = findViewById(R.id.cream);
        sugar = findViewById(R.id.sugar);
        whippedCream = findViewById(R.id.whippedCream);

        coffeeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object listItem = coffeeList.getItemAtPosition(position);
                removeCoffee(listItem);
            }
        });

    }

    public void orderCoffee(){
        String size = (String) sizeSpinner.getSelectedItem();
        int quantity = Integer.parseInt((String) numCoffees.getSelectedItem());
        Boolean[] addOns = {false,false,false};
        double newSubtotal;
        if(cream.isChecked())
            addOns[CREAM] = true;
        else
            addOns[CREAM] = false;
        if(sugar.isChecked())
            addOns[SUGAR] = true;
        else
            addOns[SUGAR] = false;
        if(whippedCream.isChecked())
            addOns[WHIPPED_CREAM] = true;
        else
            addOns[WHIPPED_CREAM] = false;

        Coffee coffee = new Coffee(size,addOns,quantity);
        for(Coffee c:coffees){
            if(c.equalAddOns(coffee) && c.getSize()==coffee.getSize()){
                c.setQuantity(c.getQuantity()+quantity);
                newSubtotal = (Double.parseDouble((String) subtotalNum.getText())) + (coffee.getPrice()*quantity);
                coffeesAdapter.notifyDataSetChanged();
                subtotalNum.setText(df.format(newSubtotal));
                return;
            }
        }
        coffees.add(coffee);
        newSubtotal = (Double.parseDouble((String) subtotalNum.getText())) + (coffee.getPrice()*quantity);
        subtotalNum.setText(df.format(newSubtotal));
        coffeesAdapter.notifyDataSetChanged();
    }

    public void removeCoffee(final Object selected){
        final Coffee newCoffee = (Coffee) selected;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("What do you want to add/remove");
        String[] options = {"Coffee", "Cream", "Sugar","Whipped Cream"};
        builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        double newSubtotal;
                        switch (which) {
                            case 0:
                                coffees.remove(selected);
                                newSubtotal = (Double.parseDouble((String) subtotalNum.getText())) - (newCoffee.getPrice()*newCoffee.getQuantity());
                                subtotalNum.setText(df.format(newSubtotal));
                                coffeesAdapter.notifyDataSetChanged();
                                break;
                            case 1:
                                coffees.remove(selected);
                                if(newCoffee.getAddon(CREAM))
                                    newSubtotal = (Double.parseDouble((String) subtotalNum.getText())) - ADD_ON_PRICE;
                                else
                                    newSubtotal = (Double.parseDouble((String) subtotalNum.getText())) + ADD_ON_PRICE;
                                newCoffee.swapAddOn(CREAM);
                                coffees.add(newCoffee);
                                subtotalNum.setText(df.format(newSubtotal));
                                coffeesAdapter.notifyDataSetChanged();
                                break;
                            case 2:
                                coffees.remove(selected);
                                if(newCoffee.getAddon(SUGAR))
                                    newSubtotal = (Double.parseDouble((String) subtotalNum.getText())) - ADD_ON_PRICE;
                                else
                                    newSubtotal = (Double.parseDouble((String) subtotalNum.getText())) + ADD_ON_PRICE;
                                newCoffee.swapAddOn(SUGAR);
                                coffees.add(newCoffee);
                                subtotalNum.setText(df.format(newSubtotal));
                                coffeesAdapter.notifyDataSetChanged();
                                break;
                            case 3:
                                coffees.remove(selected);
                                if(newCoffee.getAddon(WHIPPED_CREAM))
                                    newSubtotal = (Double.parseDouble((String) subtotalNum.getText())) - ADD_ON_PRICE;
                                else
                                    newSubtotal = (Double.parseDouble((String) subtotalNum.getText())) + ADD_ON_PRICE;
                                subtotalNum.setText(df.format(newSubtotal));
                                newCoffee.swapAddOn(WHIPPED_CREAM);
                                coffees.add(newCoffee);
                                coffeesAdapter.notifyDataSetChanged();
                                break;
                        }
                        coffeesAdapter.notifyDataSetChanged();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
