package com.example.saleclient;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread implements Runnable {

    // Communication parameters
    Socket socket;
    ServerSocket serverSocket;
    InputStreamReader inputStreamReader;
    BufferedReader bufferedReader;
    Handler handler = new Handler();
    Context context;
    public static String jsonProduct = "{'products':[" +
            "{'ProductName':'Test1','UnitPrice':1.11, 'VatRate':0}," +
            "{'ProductName':'Test2','UnitPrice':22.0, 'VatRate':8}," +
            "{'ProductName':'Test3','UnitPrice':12.0, 'VatRate':1}]}";

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(7801);
            while (true) {
                socket = serverSocket.accept();
                inputStreamReader = new InputStreamReader(socket.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                jsonProduct = bufferedReader.readLine();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "Ürünler indirildi.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
