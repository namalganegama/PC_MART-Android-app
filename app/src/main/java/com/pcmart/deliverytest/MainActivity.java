package com.pcmart.deliverytest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button add;
    ListView listView;
    TextView count;
    Context context;
    private DbHandler dbHandler;
    private List<Delivery_Model> deliveries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        dbHandler = new DbHandler(this);
        add = findViewById(R.id.add);
        listView = findViewById(R.id.deliverylist);
        count = findViewById(R.id.deliverycount);
        context = this;
        deliveries = new ArrayList<>();

        deliveries = dbHandler.getAllDeliveries();

        //display dilivery list
        DeliveryAdapter adapter = new DeliveryAdapter(context,R.layout.singledelivery,deliveries);
        listView.setAdapter(adapter);



        //get delivery count
        int countDelivery = dbHandler.countDelivery();
        count.setText("You have "+countDelivery+" deliveries");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, AddDelivery.class));
            }
        });

        //display alert box
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Delivery_Model dilivery = deliveries.get(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delivery details");
                builder.setMessage("Name       : "+dilivery.getName()+ "\n" +"Phone No: "+dilivery.getPhone()+"\n"+"Email        : "+dilivery.getEmail()+
                        "\n"+"Address   : "+dilivery.getAddress()+"\n"+"Zip Code  : "+dilivery.getZipcode()+"\n"+"Quentity   : "+dilivery.getQuentity()+"\n");

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHandler.deleteDelivery(dilivery.getId());
                        startActivity(new Intent(context, MainActivity.class));

                        Toast.makeText(MainActivity.this,
                                "Delivery Deleted Successfully..",
                                Toast.LENGTH_LONG).show();
                    }
                });

                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context, EditDelivery.class);
                        intent.putExtra("id",String.valueOf(dilivery.getId()));
                        startActivity(intent);
                    }
                });
                builder.show();


            }
        });
    }
}