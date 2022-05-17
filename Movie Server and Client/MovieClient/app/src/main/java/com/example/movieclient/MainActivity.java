package com.example.movieclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button bind;
    Button unbind;
    TextView status;
    TextView edit;
    Button show;

    RadioButton r1;
    RadioButton r2;
    RadioButton r3;
    RadioButton r4;
    RadioButton r5;

    Boolean b=false;

    private movieInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bind=findViewById(R.id.button);
        unbind=findViewById(R.id.button2);
        status=findViewById(R.id.textView);
        edit=findViewById(R.id.movInfo);
        status.setText("Not Binded");
        unbind.setEnabled(b);
        show=findViewById(R.id.show);

        r1=findViewById(R.id.radio1);
        r2=findViewById(R.id.radio2);
        r3=findViewById(R.id.radio3);
        r4=findViewById(R.id.radio4);
        r5=findViewById(R.id.radio5);

        r1.setVisibility(View.INVISIBLE);
        r2.setVisibility(View.INVISIBLE);
        r3.setVisibility(View.INVISIBLE);
        r4.setVisibility(View.INVISIBLE);
        r5.setVisibility(View.INVISIBLE);
        show.setVisibility(View.INVISIBLE);


        bind.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    Onpressbind();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        unbind.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    Onpressunbind();
            }
        });


        WebView mView = (WebView) findViewById(R.id.web) ;
        r1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    mView.loadUrl(info.getUrl(0));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                Map mov = null;
                try {
                    mov = info.getone(0);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                String r = "Movie Name: " + mov.get("Titles") + "\n \n Director: " + mov.get("Directors");
                edit.setText(r);

            }
        });
        r2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    mView.loadUrl(info.getUrl(1));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                Map mov = null;
                try {
                    mov = info.getone(1);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                String r = "Movie Name: " + mov.get("Titles") + "\n \n Director: " + mov.get("Directors");
                edit.setText(r);
            }
        });
        r3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    mView.loadUrl(info.getUrl(2));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                Map mov = null;
                try {
                    mov = info.getone(2);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                String r = "Movie Name: " + mov.get("Titles") + "\n \n Director: " + mov.get("Directors");
                edit.setText(r);

            }
        });
        r4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    mView.loadUrl(info.getUrl(3));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                Map mov = null;
                try {
                    mov = info.getone(3);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                String r = "Movie Name: " + mov.get("Titles") + "\n \n Director: " + mov.get("Directors");
                edit.setText(r);

            }
        });
        r5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    mView.loadUrl(info.getUrl(4));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                Map mov = null;
                try {
                    mov = info.getone(4);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                String r = "Movie Name: " + mov.get("Titles") + "\n \n Director: " + mov.get("Directors");
                edit.setText(r);

            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                try {
                    intent.putStringArrayListExtra("m", (ArrayList<String>) info.getall().get("Titles"));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                try {
                    intent.putStringArrayListExtra("d",(ArrayList<String>) info.getall().get("Directors") );
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                try {
                    intent.putStringArrayListExtra("u",(ArrayList<String>) info.getall().get("Urls") );
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
            }
        });
    }



    public void onbind() throws RemoteException {
        if (b) {
            status.setText("Service Binded");
            unbind.setEnabled(b);
            r1.setVisibility(View.VISIBLE);
            r2.setVisibility(View.VISIBLE);
            r3.setVisibility(View.VISIBLE);
            r4.setVisibility(View.VISIBLE);
            r5.setVisibility(View.VISIBLE);
            edit.setVisibility(View.VISIBLE);
            show.setVisibility(View.VISIBLE);
        }
    }


    private final ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder iservice) {
            info = movieInfo.Stub.asInterface(iservice);
            b = true;
            try {
                onbind();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            info=null;
            b=false;
            status.setText("Not Binded");
            unbind.setEnabled(b);
            edit.setVisibility(View.INVISIBLE);
            r1.setVisibility(View.INVISIBLE);
            r2.setVisibility(View.INVISIBLE);
            r3.setVisibility(View.INVISIBLE);
            r4.setVisibility(View.INVISIBLE);
            r5.setVisibility(View.INVISIBLE);
            show.setVisibility(View.INVISIBLE);
            edit.setText("");
            Intent i = new Intent(movieInfo.class.getName());
            ResolveInfo info = getPackageManager().resolveService(i, 0);
            i.setComponent(new ComponentName("com.example.server", "com.example.server.movieService"));
            stopService(i);
        }
    };

    public void Onpressbind() throws RemoteException {
        if (!b) {
            boolean x;

            Intent i = new Intent(movieInfo.class.getName());
            ResolveInfo info = getPackageManager().resolveService(i, 0);
            i.setComponent(new ComponentName("com.example.server", "com.example.server.movieService"));
            x = bindService(i, this.mConnection, Context.BIND_AUTO_CREATE);
            if (x) {
                Log.i("a", "bindService() succeeded!");
            } else {
                Log.i("b", "bindService() failed!");
            }
        }
    }

    public void Onpressunbind() {
        if (b) {
            unbindService(mConnection);
            b = false;
            status.setText("Not Binded");
            unbind.setEnabled(b);
            edit.setVisibility(View.INVISIBLE);
            r1.setVisibility(View.INVISIBLE);
            r2.setVisibility(View.INVISIBLE);
            r3.setVisibility(View.INVISIBLE);
            r4.setVisibility(View.INVISIBLE);
            r5.setVisibility(View.INVISIBLE);
            show.setVisibility(View.INVISIBLE);
            edit.setText("");
            Intent i = new Intent(movieInfo.class.getName());
            ResolveInfo info = getPackageManager().resolveService(i, 0);
            i.setComponent(new ComponentName("com.example.server", "com.example.server.movieService"));
            stopService(i);
        }
    }

}