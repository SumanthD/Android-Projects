package com.example.broadcast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String Restaurant_INTENT =
            "edu.uic.cs478.Restaurant";
    private static final String Tourism_INTENT =
            "edu.uic.cs478.Tourism";
    private static final String PERMISSION =
            "edu.uic.cs478.Permission" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button) findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Open Restaurants Intent", Toast.LENGTH_SHORT).show();
                checkPermissionAndBroadcast();
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Open Tourism Intent", Toast.LENGTH_SHORT).show();
                checkPermissionAndBroadcast2();
            }
        });

    }

    private void checkPermissionAndBroadcast() {
        if (ActivityCompat.checkSelfPermission(this, PERMISSION)
                == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getBaseContext(), "Permissions already granted", Toast.LENGTH_SHORT).show();
            Intent aIntent = new Intent(Restaurant_INTENT);
            sendOrderedBroadcast(aIntent, PERMISSION);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{PERMISSION}, 0);
        }

    }

    private void checkPermissionAndBroadcast2() {
        if (ActivityCompat.checkSelfPermission(this, PERMISSION)
                == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getBaseContext(), "Permissions already granted", Toast.LENGTH_SHORT).show();
            Intent aIntent = new Intent(Tourism_INTENT);
            sendOrderedBroadcast(aIntent, PERMISSION);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{PERMISSION}, 1);
        }

    }

    public void onRequestPermissionsResult(int code, String[] permissions, int[] results) {
        super.onRequestPermissionsResult(code, permissions, results);
        if (results.length > 0) {
            if (results[0] == PackageManager.PERMISSION_GRANTED) {
                if (code == 0) {
                    Intent aIntent = new Intent(Restaurant_INTENT);
                    sendOrderedBroadcast(aIntent, PERMISSION);
                }
                if (code==1) {
                    Intent aIntent = new Intent(Tourism_INTENT);
                    sendOrderedBroadcast(aIntent, PERMISSION);
                }
            } else {
                Toast.makeText(this, "No permission", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
}