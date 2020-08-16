package com.example.saleclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private Button buttonConnect;
    public static EditText editTextIp, editTextPort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonConnect = findViewById(R.id.buttonConnect);
        editTextIp = findViewById(R.id.editTextIP);
        editTextPort = findViewById(R.id.editTextPort);

        buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextIp.getText().toString() == "" || editTextPort.getText().toString() == ""){
                    String msg = "Lütfen Alanları Doldurunuz";
                    Toast.makeText(getApplicationContext(), msg , Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(MainActivity.this, SynchronizeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}