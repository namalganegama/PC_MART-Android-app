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

public class AddDelivery extends AppCompatActivity {

    private EditText name, phone, email, address, zipcode, quentity;
    private Button add;

    private DbHandler dbHandler;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_add_delivery);


        name = findViewById(R.id.et_name);
        phone = findViewById(R.id.et_phone);
        email = findViewById(R.id.et_email);
        address = findViewById(R.id.et_address);
        zipcode = findViewById(R.id.et_zipcode);
        quentity = findViewById(R.id.et_qty);

        add = findViewById(R.id.btn_add);

        context = this;
        dbHandler = new DbHandler(context);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = name.getText().toString();
                Integer userphone=0;
                try {
                    userphone = Integer.parseInt(phone.getText().toString());
                }catch(NumberFormatException ex){
                    System.out.println("not a number");
                }
                String useremail = email.getText().toString();
                String useraddress = address.getText().toString();
                Integer userzipcode=0;
                try {
                    userzipcode = Integer.parseInt(zipcode.getText().toString());
                }catch(NumberFormatException ex){
                    System.out.println("not a number");
                }
                Integer userquentity=0;
                try {
                     userquentity = Integer.parseInt(quentity.getText().toString());
                }catch(NumberFormatException ex){
                    System.out.println("not a number");
                }
                String vphone = String.valueOf(userphone);
                String vzipcode = String.valueOf(userzipcode);
                String vquentity = String.valueOf(userquentity);

                boolean check = validateinfo(username,vphone,useremail,useraddress,vzipcode,vquentity);

                if(check==true){

                    Toast.makeText(getApplicationContext(),"Data is valid",Toast.LENGTH_SHORT).show();

                Delivery_Model delivery = new Delivery_Model(username,userphone,useremail,useraddress,userzipcode,userquentity);
                dbHandler.addDelivery(delivery);

                startActivity(new Intent(context,MainActivity.class));

                Toast.makeText(AddDelivery.this,
                        "Delivery Inserted Successfully..",
                        Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(getApplicationContext(),"Sorry check information again",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Boolean validateinfo(String username,String vphone,String useremail,String useraddress,String userzipcode,String userquentity){
        if(username.length()==0){
            name.requestFocus();
            name.setError("FIELD CANNOT BE EMPTY");
            return false;
        }
        else if(!username.matches("[a-zA-Z]+")){
            name.requestFocus();
            name.setError("ENTER ONLY ALPHABETICAL CHARACTER");
            return false;
        }
        else if(vphone.length()==0){
            phone.requestFocus();
            phone.setError("FIELD CANNOT BE EMPTY");
            return false;
        }
        else if(!vphone.matches("^[+]?[0-9]{8,20}$")){
            phone.requestFocus();
            phone.setError("Correct Format: +94xxxxxxxx");
            return false;
        }
        else if(useremail.length()==0){
            email.requestFocus();
            email.setError("FIELD CANNOT BE EMPTY");
            return false;
        }
        else if(!useremail.matches("[a-zA-Z0-9._%+-]+@[a-z0-9]+\\.[a-z]{2,3}")){
            email.requestFocus();
            email.setError("ENTER VALID EMAIL");
            return false;
        }
        else if(useraddress.length()==0){
            email.requestFocus();
            email.setError("FIELD CANNOT BE EMPTY");
            return false;
        }
        else if(userzipcode.length()==0){
            email.requestFocus();
            email.setError("FIELD CANNOT BE EMPTY");
            return false;
        } else if(userquentity.length()==0){
            email.requestFocus();
            email.setError("FIELD CANNOT BE EMPTY");
            return false;
        }
        else{
            return true;
        }
    }
}