package com.example.dialer;

import androidx.appcompat.app.AppCompatActivity;
import java.util.regex.*;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        EditText edtText = findViewById(R.id.editTextPhone);

        edtText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String number = edtText.getText().toString().trim();
                if(isValid(number))
                {
                    Intent output = new Intent();
                    output.putExtra("result", number);
                    setResult(RESULT_OK, output);
                }
                else
                {
                    Intent output = new Intent();
                    output.putExtra("result", number);
                    setResult(RESULT_CANCELED, output);
                } finish();
                return true;
            }
        });

    }
    public static boolean isValid(String number)
    {
        return number.matches( "^\\d{9}$") || number.matches("^\\s*[(]*(\\d{3})[ )-]*(\\d{3})[- ]*(\\d{3})(?: *x(\\d+))?\\s*$");
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}


