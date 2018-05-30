package com.example.pro.driveassister;

import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ToggleButton tb;
    CheckBox ch1,ch2;
    AudioManager mAudioManager;
    IntentFilter i1,i2;
    SmsListener smsListener;
    IncomingCallReceiver callReceiver;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        //registerReceiver(smsListener,i1);
        ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch1.isChecked()) {
                    say("ch is checked");
                    registerReceiver(smsListener,i1);
                }
                else{
                    unregisterReceiver(smsListener);
                    say("ch1 is not checked");
                }
            }
        });
        ch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch2.isChecked()) {
                    say("ch2 is checked");
                    registerReceiver(callReceiver,i2);
                }
                else{
                    say("ch2 is not checked");
                    unregisterReceiver(callReceiver);
                }
            }
        });

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        smsListener = new SmsListener();
        callReceiver = new IncomingCallReceiver();

        i1=new IntentFilter();
        i1.addAction("android.provider.Telephony.SMS_RECEIVED");

        i2=new IntentFilter();
        i2.addAction("android.intent.action.PHONE_STATE");

        mAudioManager = (AudioManager)getSystemService(AUDIO_SERVICE);


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        tb = (ToggleButton)findViewById(R.id.toggleButton);
        ch1=(CheckBox)findViewById(R.id.checkBox);
        ch2=(CheckBox)findViewById(R.id.checkBox2);
        tb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tb.getText().toString().equalsIgnoreCase("SafeModeOn")){

                    if(mAudioManager.getRingerMode()==AudioManager.RINGER_MODE_VIBRATE){
                        mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                    }
                    else if(mAudioManager.getRingerMode()==AudioManager.RINGER_MODE_NORMAL){
                        mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                    }
                    else{
                        mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                    }

                    say("SafeModeOn");
                    ch1.setEnabled(true);
                    ch2.setEnabled(true);


                }
                else{
                    if(mAudioManager.getRingerMode()==AudioManager.RINGER_MODE_VIBRATE){
                        mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    }
                    else if(mAudioManager.getRingerMode()==AudioManager.RINGER_MODE_NORMAL){
                        mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    }
                    else{
                        mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    }

                    say("SafeModeOff");
                    ch1.setChecked(false);
                    ch2.setChecked(false);
                    ch1.setEnabled(false);
                    ch2.setEnabled(false);
                }

            }
        });

        ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch1.isChecked()) {
                    say("ch is checked");
                    registerReceiver(smsListener,i1);
                }
                else{
                    unregisterReceiver(smsListener);
                    say("ch1 is not checked");
                }
            }
        });
        ch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch2.isChecked()) {
                    say("ch2 is checked");
                    registerReceiver(callReceiver, i2);
                }
                else{
                    say("ch2 is not checked");
                    unregisterReceiver(callReceiver);
                }
            }
        });


    }


    public void say(String a){
        Toast.makeText(MainActivity.this, a, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(smsListener);
        unregisterReceiver(callReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.missedcall12) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
