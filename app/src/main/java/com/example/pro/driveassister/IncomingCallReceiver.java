package com.example.pro.driveassister;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Created by aravind on 03-12-2015.
 */
public class IncomingCallReceiver extends BroadcastReceiver {

    static boolean ring = false;
    static boolean callReceived = false;
    String callerPhoneNumber;

    @Override
    public void onReceive(Context mContext, Intent intent) {

        // Get the current Phone State
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

        if (state == null)
            return;

        // If phone state "Rininging"
        if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            ring = true;
            // Get the Caller's Phone Number
            Bundle bundle = intent.getExtras();
            callerPhoneNumber = bundle.getString("incoming_number");
            Toast.makeText(mContext, callerPhoneNumber, Toast.LENGTH_LONG);
        }


        // If incoming call is received
        if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
            callReceived = true;
        }


        // If phone is Idle
        if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
            // If phone was ringing(ring=true) and not received(callReceived=false) , then it is a missed call
            if (ring == true && callReceived == false) {
                Toast.makeText(mContext, "It was A MISSED CALL from : " + callerPhoneNumber, Toast.LENGTH_LONG).show();
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(callerPhoneNumber,null,"Iam driving call me later",null,null);
                Toast.makeText(mContext,"Message sent!",Toast.LENGTH_LONG);
            }
        }
    }
}
