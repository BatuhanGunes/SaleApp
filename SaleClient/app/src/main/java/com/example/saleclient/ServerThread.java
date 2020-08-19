package com.example.saleclient;

import android.os.Handler;
import android.util.Log;
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
    public static String incomingMessage;

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(7801);
            while (true) {
                socket = serverSocket.accept();
                inputStreamReader = new InputStreamReader(socket.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                incomingMessage = bufferedReader.readLine();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("Incoming Message", "\"" + incomingMessage + "\"");
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
