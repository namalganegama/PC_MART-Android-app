package com.example.pc_mart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    EditText _txtUser, _txtPass;
    Button _btnLogin, _btnReg;
    Spinner _spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        _txtPass=(EditText) findViewById(R.id.txtPass);
        _txtUser=(EditText) findViewById(R.id.txtUser);
        _btnLogin=(Button) findViewById(R.id.btnLogin);
        _btnReg = (Button) findViewById(R.id.btnReg);
        _spinner=(Spinner) findViewById(R.id.spinner);

        _btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Register.class);
                startActivity(i);
            }
        });
        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.usertype, R.layout.support_simple_spinner_dropdown_item);
        _spinner.setAdapter(adapter);
        _btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = _spinner.getSelectedItem().toString();
                if (_txtUser.getText().toString().equals("Seller")&& _txtPass.getText().toString().equals("Seller")&& item.equals("Seller")) {
                    Intent intent = new Intent(MainActivity.this, Seller.class);
                    startActivity(intent);

                }else if (_txtUser.getText().toString().equals("Seller")&& _txtPass.getText().toString().equals("Seller")&& item.equals("User")){
                    Intent intent = new Intent(MainActivity.this, User.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}