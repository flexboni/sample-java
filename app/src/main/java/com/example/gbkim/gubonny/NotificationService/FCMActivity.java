package com.example.gbkim.gubonny.NotificationService;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.gbkim.gubonny.R;
import com.google.firebase.iid.FirebaseInstanceId;

public class FCMActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcm);

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("FCM_Token", token);


    }
}
