package com.mirzaki.onoff;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import cn.wch.ch34xuartdriver.CH34xUARTDriver;

public class App extends Application {

    public static final String CHANNEL_ID = "sampleChannelId";

    public static CH34xUARTDriver driver;

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channelService = new NotificationChannel(
                    CHANNEL_ID,
                    "Example Channel Service",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channelService);
        }
    }
}
