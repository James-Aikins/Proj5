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
        Boolean[] addOns = new Boolean[3];
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
                coffeesAdapter.notifyDataSetChanged();
                return;
            }
        }
        coffees.add(coffee);
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
                        switch (which) {
                            case 0:
                                coffees.remove(selected);
                                coffeesAdapter.notifyDataSetChanged();
                                break;
                            case 1:
                                coffees.remove(selected);
                                newCoffee.swapAddOn(CREAM);
                                coffees.add(newCoffee);
                                coffeesAdapter.notifyDataSetChanged();
                                break;
                            case 2:
                                coffees.remove(selected);
                                newCoffee.swapAddOn(SUGAR);
                                coffees.add(newCoffee);
                                coffeesAdapter.notifyDataSetChanged();
                                break;
                            case 3:
                                coffees.remove(selected);
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
