package course.examples.UI.GridLayout;

import static course.examples.UI.GridLayout.GridLayoutActivity.EXTRA_RES_ID;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class FactsViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts_view);

        Intent intent = getIntent();
        String res=intent.getExtras().getString("f");
        TextView tw = (TextView) findViewById(R.id.textView);
        tw.setText(res);

    }
}