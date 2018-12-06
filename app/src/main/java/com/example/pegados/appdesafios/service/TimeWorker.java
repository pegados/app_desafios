package com.example.pegados.appdesafios.service;

import android.os.SystemClock;
import android.util.Log;

public class TimeWorker implements Runnable {

    private volatile boolean running;
    private int seconds;

    @Override
    public void run() {
        running = true;
        while (running){
            //seconds++;
            incrementSeconds();
            SystemClock.sleep(1000);
        }
    }

    public void stop(){
        running = false;
    }

    private synchronized void incrementSeconds(){
        seconds++;
    }

    public synchronized int getSeconds(){
        return seconds;
    }
}
