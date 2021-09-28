package com.example.listingitem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class viewListing extends AppCompatActivity {
    ListView ListView1;
    EditText InputText1;
    Button btnvl1, btnvl2;

    ArrayList<String> Items = new ArrayList<String>();
    ArrayAdapter myAdapter1;

    Integer indexVal;
    String item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_listing);

        ListView listView1 = (ListView) findViewById(R.id.listView1);
        btnvl1 = (Button) findViewById(R.id.btnvl1);
        btnvl2 = (Button) findViewById(R.id.btnvl2);
        InputText1 = (EditText) findViewById(R.id.InputText1);

        //setup listview
        Items.add("keyboard");
        Items.add("mouse");

        myAdapter1 = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, Items);
        listView1.setAdapter(myAdapter1);


        //add item

        btnvl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringval = InputText1.getText().toString();
                Items.add(stringval);
                myAdapter1.notifyDataSetChanged();

                InputText1.setText("");


                //select item

                listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        item = adapterView.getItemAtPosition(i).toString() + "has been selected";
                        indexVal = i;
                        Toast.makeText(viewListing.this, item, Toast.LENGTH_SHORT).show();
                    }
                });
                //update
                btnvl2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String stringval = InputText1.getText().toString();
                        Items.set(indexVal, stringval);
                    }

                });

            }
        });
    }
}