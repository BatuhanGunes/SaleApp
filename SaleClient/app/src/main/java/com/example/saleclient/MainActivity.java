package com.example.saleclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonConnect;
    public static EditText editTextIp, editTextPort;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonConnect = findViewById(R.id.buttonConnect);
        editTextIp = findViewById(R.id.editTextIP);
        editTextPort = findViewById(R.id.editTextPort);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((editTextIp.getText().toString() == "" || editTextPort.getText().toString() == "")
                        && (editTextIp.getText().toString() == "" && editTextPort.getText().toString() == "")){
                    String msg = "Lütfen Alanları Doldurunuz";
                    Toast.makeText(getApplicationContext(), msg , Toast.LENGTH_SHORT).show();
                }else{
                    progressBar.setVisibility(View.VISIBLE);
                    String msg = "connectionTest-null";
                    MessageSender messageSender = new MessageSender();
                    messageSender.execute(msg);

                    // Start communication. Constantly reads data from the server
                    Thread conThread = new Thread(new ServerThread());
                    conThread.start();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);
                            String connectionMessage = ServerThread.incomingMessage;
                            if(connectionMessage != null){
                                String ToastMsg = "Bağlantı Başarılı.";
                                Toast.makeText(getApplicationContext(), ToastMsg , Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, SynchronizeActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                String ToastMsg = " Bağlantı Başarısız. Lütfen Tekrar Deneyiniz.";
                                Toast.makeText(getApplicationContext(), ToastMsg , Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, 1000);
                }
            }
        });
    }
}