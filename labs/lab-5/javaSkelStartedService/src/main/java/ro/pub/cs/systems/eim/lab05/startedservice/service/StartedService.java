package ro.pub.cs.systems.eim.lab05.startedservice.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import ro.pub.cs.systems.eim.lab05.startedservice.general.Constants;

public class StartedService extends Service {

    private static final String TAG = "ForegroundService";
    private static final String CHANNEL_ID = "11";
    private static final String CHANNEL_NAME = "ForegroundServiceChannel";
    private void dummyNotification() {
        // Create channel first
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_LOW
            );
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
        }

        // Create a proper notification with content
        Notification notification;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification = new Notification.Builder(getApplicationContext(), CHANNEL_ID)
                    .setContentTitle("Service Running")
                    .setContentText("Processing in background")
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .build();
        } else {
            notification = new Notification.Builder(getApplicationContext())
                    .setContentTitle("Service Running")
                    .setContentText("Processing in background")
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .build();
        }

        startForeground(1, notification);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        dummyNotification();
        Log.d(Constants.TAG, "onCreate() method was invoked");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(Constants.TAG, "onBind() method was invoked");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(Constants.TAG, "onUnbind() method was invoked");
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(Constants.TAG, "onRebind() method was invoked");
    }

    @Override
    public void onDestroy() {
        Log.d(Constants.TAG, "onDestroy() method was invoked");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(Constants.TAG, "onStartCommand() method was invoked");

        dummyNotification();

        ProcessingThread thread = new ProcessingThread(this);
        thread.start();

        return START_REDELIVER_INTENT;
    }

}
