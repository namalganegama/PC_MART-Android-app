package com.example.pc_mart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pc_mart.Database.DBHandler;

import java.util.List;

public class ProfileManagement extends AppCompatActivity {
    EditText regName,regEmail,regPhone,regUser,regPass;
    Button btnEd,btnDel,btnSer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        regName = findViewById(R.id.regName);
        regEmail = findViewById(R.id.regEmail);
        regPhone = findViewById(R.id.regPhone);
        btnEd = findViewById(R.id.btnEd);
        btnDel = findViewById(R.id.btnDel);
        btnSer = findViewById(R.id.btnSer);

        btnSer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                List user = dbHandler.readAllInfo(regUser.getText().toString());

                if (user.isEmpty()) {
                    Toast.makeText(ProfileManagement.this, "No User", Toast.LENGTH_SHORT).show();
                    regUser.setText(null);
                }
                else {
                    Toast.makeText(ProfileManagement.this, "User Found! User"+user.get(3).toString(), Toast.LENGTH_SHORT).show();
                    regName.setText(user.get(0).toString());
                    regEmail.setText(user.get(1).toString());
                    regPhone.setText(user.get(2).toString());
                    regUser.setText(user.get(3).toString());
                    regPass.setText(user.get(4).toString());
                }
            }
        });
        btnEd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());

                Boolean status = dbHandler.updateInfo(regName.getText().toString(), regEmail.getText().toString(),regPhone.getText().toString(), regUser.getText().toString(),regPass.getText().toString());
                if (status){
                    Toast.makeText(ProfileManagement.this, "User Updated", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ProfileManagement.this, "Update Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                dbHandler.deleteInfo(regUser.getText().toString());

                Toast.makeText(ProfileManagement.this, "User Deleted", Toast.LENGTH_SHORT).show();

                regName.setText(null);
                regEmail.setText(null);
                regPhone.setText(null);
                regUser.setText(null);
                regPass.setText(null);
            }
        });
    }
}