package com.example.listingitem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView ListView1;
    EditText InputText1;
    Button btnli, btnli2;

    ArrayList<String> Items =new ArrayList<String>();
    ArrayAdapter myAdapter1;

    Integer indexVal;
    String item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView1 = (ListView) findViewById(R.id.listView1);
        btnli=(Button) findViewById(R.id.btnli);
        btnli2=(Button) findViewById(R.id.btnli2);
        InputText1=(EditText) findViewById(R.id.ed_txt);
        //setup listview
        Items.add("keyboard");
        Items.add("mouse");

        myAdapter1=new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, Items);
        listView1.setAdapter(myAdapter1);


        //add item

        btnli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringval = InputText1.getText().toString();
                Items.add(stringval);
                myAdapter1.notifyDataSetChanged();

                InputText1.setText("");


            }
        });

        //select item

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                item=adapterView.getItemAtPosition(i).toString() + "has been selected";
                indexVal=i;
                Toast.makeText(MainActivity.this, item,Toast.LENGTH_SHORT).show();
            }
        });
        //update
        btnli2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringval = InputText1.getText().toString();
                Items.set(indexVal,stringval);
            }

        });

        //delete
        ListView1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item =adapterView.getItemAtPosition(i).toString()+"has been deleted";
                Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
                Items.remove (i);
                myAdapter1.notifyDataSetChanged();


                return true;
            }
        });
              }

        }



///////////////////
