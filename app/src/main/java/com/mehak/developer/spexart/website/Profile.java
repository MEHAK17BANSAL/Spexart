package com.mehak.developer.spexart.website;

import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mehak.developer.spexart.R;

public class Profile extends AppCompatActivity {
    LinearLayout call;

    void initViews() {
        call = findViewById(R.id.callpad);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+123456"));
                if(ActivityCompat.checkSelfPermission(Profile.this, android.Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(Profile.this, "Please on the phone permission", Toast.LENGTH_LONG).show();

                }
                else {
                    startActivity(intent);
                }

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(101);
       // firestore = FirebaseFirestore.getInstance();
       // auth = FirebaseAuth.getInstance();
       // uid = auth.getCurrentUser().getUid();
        initViews();
    }

    public void about(View view) {
        Intent intent = new Intent(Profile.this,MainActivity.class);
        startActivity(intent);
    }
}
