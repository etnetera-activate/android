package cz.activate.gpstosms;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String number = "00420777959065";
    private String number2 = "00420723580366";
    String message = "Testing my new app";
    final int SEND_SMS_PERMISSION_REQUEST_CODE = 111;
    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        Button button_loc2 = (Button) findViewById(R.id.send_my_loc);
        button_loc2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                sendSMS(number, message);
            }
        });
    }

    //---sends an SMS message to another device---
    @SuppressWarnings("deprecation")
    private void sendSMS(String number, String message) {
        Log.v("phoneNumber", number);
        Log.v("Message", message);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE)
                    == PackageManager.PERMISSION_DENIED) {

                Log.d("permission", "permission denied to phone state - requesting it");
                String[] permissions = {Manifest.permission.READ_PHONE_STATE};

                requestPermissions(permissions, PERMISSION_REQUEST_CODE);
                Log.d("permission", "zadam o pristup");

            }
            if (checkSelfPermission(Manifest.permission.SEND_SMS)
                    == PackageManager.PERMISSION_DENIED) {

                Log.d("permission", "permission denied to SEND_SMS - requesting it");
                String[] permissions = {Manifest.permission.SEND_SMS};

                requestPermissions(permissions, PERMISSION_REQUEST_CODE);
                Log.d("permission", "zadam o pristup");

            }

        }




        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(number, null, message, null, null);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 123) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("PLAYGROUND", "Permission has been granted");
                Toast.makeText(this, "You can send SMS!",Toast.LENGTH_SHORT).show();

            } else {
                Log.d("PLAYGROUND", "Permission has been denied or request cancelled");
                Toast.makeText(this, "You cannot send SMS!",Toast.LENGTH_SHORT).show();

            }
        }
    }





}
