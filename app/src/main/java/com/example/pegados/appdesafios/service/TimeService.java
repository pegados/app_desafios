package com.example.pegados.appdesafios.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class TimeService extends Service {
    private TimeWorker worker;

    private TimeServiceBind binder = new TimeServiceBind();

    @Override
    public void onCreate() {
        super.onCreate();

        worker = new TimeWorker();
        Log.i("MyApp", "Serviço Iniciado");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(worker).start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        worker.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;

    }

    public int getSeconds(){
        return worker.getSeconds();
    }

    public class TimeServiceBind extends Binder{
        public TimeService getService(){
            return TimeService.this;
        }
    }
}
