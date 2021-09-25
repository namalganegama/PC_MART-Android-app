package com.example.pc_mart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pc_mart.Database.DBHandler;

public class Register extends AppCompatActivity {
    EditText regName,regEmail,regPhone,regUser,regPass;
    Button btnAdd,btnUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regName = findViewById(R.id.regName);
        regEmail = findViewById(R.id.regEmail);
        regPhone = findViewById(R.id.regPhone);
        regUser = findViewById(R.id.regUser);
        regPass = findViewById(R.id.regPass);
        btnAdd = findViewById(R.id.btnAdd);
        btnUp = findViewById(R.id.btnUp);

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ProfileManagement.class);
                startActivity(i);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                long newID = dbHandler.addInfo(regName.getText().toString(),regEmail.getText().toString(),regPhone.getText().toString(),regUser.getText().toString(),regPass.getText().toString());
                Toast.makeText(Register.this, "User Added. User ID:"+newID, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(),ProfileManagement.class);
                startActivity(i);
                regName.setText(null);
                regEmail.setText(null);
                regPhone.setText(null);
                regUser.setText(null);
                regPass.setText(null);
            }
        });
    }
}