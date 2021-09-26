package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class cashonorvisa extends AppCompatActivity {


    Button craditcard,cashond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashonorvisa);


        craditcard = findViewById(R.id.button4);
        cashond = findViewById(R.id.button5);

        craditcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),visa.class);
                startActivity(i);
            }
        });
    }
}