package com.pcmart.deliverytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditDelivery extends AppCompatActivity {

    private EditText name, phone, email, address, zipcode, quentity;
    private Button edit;
    private DbHandler dbHandler;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_edit_delivery);


        context = this;
        dbHandler = new DbHandler(context);

        name = findViewById(R.id.edit_name);
        phone = findViewById(R.id.edit_phone);
        email = findViewById(R.id.edit_email);
        address = findViewById(R.id.edit_address);
        zipcode = findViewById(R.id.edit_zipcode);
        quentity = findViewById(R.id.edit_qty);
        edit = findViewById(R.id.btn_edit);


        final String id = getIntent().getStringExtra("id");
        Delivery_Model delivery = dbHandler.getSingleDelivery(Integer.parseInt(id));

        name.setText(delivery.getName());
        phone.setText(Integer.toString(delivery.getPhone()));
        email.setText(delivery.getEmail());
        address.setText(delivery.getAddress());
        zipcode.setText(Integer.toString(delivery.getZipcode()));
        quentity.setText(Integer.toString(delivery.getQuentity()));

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameText = name.getText().toString();
                String phoneText = phone.getText().toString();
                String emailText = email.getText().toString();
                String addressText = address.getText().toString();
                String zipcodeText = zipcode.getText().toString();
                String quentityText = quentity.getText().toString();



                Delivery_Model delivery = new Delivery_Model(Integer.parseInt(id),nameText,Integer.parseInt(phoneText),
                        emailText,addressText,Integer.parseInt(zipcodeText),Integer.parseInt(quentityText));
                int state = dbHandler.updateSingleDelivery(delivery);
                System.out.println(state);
                startActivity(new Intent(context,MainActivity.class));

                Toast.makeText(EditDelivery.this,
                        "Delivery Updated Successfully..",
                        Toast.LENGTH_LONG).show();

            }
        });


    }
}