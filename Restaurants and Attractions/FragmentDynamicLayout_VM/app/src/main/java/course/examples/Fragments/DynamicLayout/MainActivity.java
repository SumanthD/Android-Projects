package course.examples.Fragments.DynamicLayout;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Receiver_R receiver1;
    private Receiver_T receiver2;
    private IntentFilter intent1;
    private IntentFilter intent2;
    private final int flag = 1;

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

        checkPermissionAndBroadcast();

        receiver1 = new Receiver_R();
        intent1 = new IntentFilter(Restaurant_INTENT);
        registerReceiver(receiver1,intent1);

        receiver2= new Receiver_T();
        intent2 = new IntentFilter(Tourism_INTENT);
        registerReceiver(receiver2,intent2);

    }
    private void checkPermissionAndBroadcast() {
        if (ActivityCompat.checkSelfPermission(this, PERMISSION)
                == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }
        else {
            requestPermissions(new String[]{PERMISSION},flag);
        }

    }

    public void onRequestPermissionsResult(int code, String[] permissions, int[] results) {
        super.onRequestPermissionsResult(code, permissions, results);
        if (results.length > 0) {
            if (results[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission Not Granted", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
}