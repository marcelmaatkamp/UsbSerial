/**
 * Created by i.maslennikov on 13.01.2017.
 */

package com.felhr.serialportexample;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;


public class CitroenBroadcastReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case "android.intent.action.BOOT_COMPLETE": // Boot completed
                context.startService(new Intent(context, UsbService.class));
                break;
        }
    }

}