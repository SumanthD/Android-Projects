package com.example.dialer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String res;
    int rescode=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enter(View View)
    {
        startActivityForResult((new Intent(this, MainActivity2.class)),1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        rescode=resultCode;
        res=data.getExtras().getString("result");
    }

    public void dial(View View)
    {
        if(rescode==0)
            Toast.makeText(getApplicationContext(), "Invalid number: "+res, Toast.LENGTH_LONG).show();

        else
        {
            Uri u = Uri.parse("tel:" + res);
            Intent i = new Intent(Intent.ACTION_DIAL, u);
            startActivity(i);
        }
    }



}

