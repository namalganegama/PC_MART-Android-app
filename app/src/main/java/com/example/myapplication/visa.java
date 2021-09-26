package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.database.DBHelper;

import java.util.List;

public class visa extends AppCompatActivity {
    EditText editTextTextPersonName2, editTextTextPersonName3,editTextTextPersonName4,editTextTextPersonName5;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visa);
        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
        editTextTextPersonName3 = findViewById(R.id.editTextTextPersonName3);
        editTextTextPersonName4 = findViewById(R.id.editTextTextPersonName4);
        editTextTextPersonName5 = findViewById(R.id.editTextTextPersonName5);

    }

    public void  saveUser(View view){
        String cardnumber = editTextTextPersonName2.getText().toString();
        String ownername = editTextTextPersonName3.getText().toString();
        String expiredate = editTextTextPersonName4.getText().toString();
        String postcord = editTextTextPersonName5.getText().toString();
        DBHelper dbHelper = new DBHelper(this);

        if (cardnumber.isEmpty()||ownername.isEmpty()||expiredate.isEmpty()||postcord.isEmpty()) {
            Toast.makeText(this, "enter card details", Toast.LENGTH_SHORT).show();

        }else {
            long inserted = dbHelper.addInfo(cardnumber,ownername,expiredate,postcord);

            if (inserted>0){
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
            }


    }

    }
    public  void  viewAll(View view){
        DBHelper dbHelper = new DBHelper(this);

        List info =dbHelper.readAll();

        String[] infoArray = (String[]) info.toArray(new String[0]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("CARD DETAILS");

        builder.setItems(infoArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String cardnumber = infoArray[i].split(":")[0];
                Toast.makeText(visa.this,cardnumber, Toast.LENGTH_SHORT).show();

            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void  deleteUser(View view) {
        DBHelper dbHelper = new DBHelper(this);
        String cardnumber = editTextTextPersonName2.getText().toString();

        if (cardnumber.isEmpty()) {
            Toast.makeText(this, "select card", Toast.LENGTH_SHORT).show();
        } else {
            dbHelper.deleteInfo(cardnumber);

            Toast.makeText(this, cardnumber+ "card is deleted",Toast.LENGTH_SHORT).show();
        }

    }
    public void  updateUser(View view) {
        DBHelper dbHelper = new DBHelper(this);
        String cardnumber = editTextTextPersonName2.getText().toString();

        if (cardnumber.isEmpty()) {
            Toast.makeText(this, "select or type card", Toast.LENGTH_SHORT).show();
        } else {
            dbHelper.deleteInfo(cardnumber);

            Toast.makeText(this, cardnumber+ "card is update",Toast.LENGTH_SHORT).show();
        }

    }
}