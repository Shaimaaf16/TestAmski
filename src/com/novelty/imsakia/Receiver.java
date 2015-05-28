package com.novelty.imsakia;

import com.parse.ParseBroadcastReceiver;

import android.content.Context;
import android.content.Intent;

public class Receiver extends ParseBroadcastReceiver {
@Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, MainActivity.class);
        i.putExtras(intent.getExtras());
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

}