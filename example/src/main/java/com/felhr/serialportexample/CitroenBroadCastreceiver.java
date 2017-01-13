package com.felhr.serialportexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


/**
 * Created by i.maslennikov on 13.01.2017.
 */

public class CitroenBroadcastReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, UsbService.class));
    }
}