package course.examples.Fragments.DynamicLayout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Receiver_R extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent rest_int = new Intent(context, QuoteViewerActivity.class);
        context.startActivity(rest_int);
    }
}