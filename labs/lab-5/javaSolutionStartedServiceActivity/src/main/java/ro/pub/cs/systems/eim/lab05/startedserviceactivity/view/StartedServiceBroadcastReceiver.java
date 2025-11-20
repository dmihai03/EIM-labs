package ro.pub.cs.systems.eim.lab05.startedserviceactivity.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import ro.pub.cs.systems.eim.lab05.startedserviceactivity.general.Constants;

public class StartedServiceBroadcastReceiver extends BroadcastReceiver {

    private TextView messageTextView;

    // TODO: exercise 10 - default constructor
    public StartedServiceBroadcastReceiver() {
        this.messageTextView = null;
    }

    public StartedServiceBroadcastReceiver(TextView messageTextView) {
        this.messageTextView = messageTextView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: exercise 7 - get the action and the extra information from the intent
        // and set the text on the messageTextView

        String action = intent.getAction();
        Log.d(Constants.TAG, "onReceive called with action: " + action);
        String data = null;
        if (Constants.ACTION_STRING.equals(action)) {
            data = intent.getStringExtra(Constants.DATA);
        }
        if (Constants.ACTION_INTEGER.equals(action)) {
            data = String.valueOf(intent.getIntExtra(Constants.DATA, -1));
        }
        if (Constants.ACTION_ARRAY_LIST.equals(action)) {
            java.util.ArrayList<String> arrayList = intent.getStringArrayListExtra(Constants.DATA);
            if (arrayList != null) {
                data = arrayList.toString();
            }
        }
        if (messageTextView != null && data != null) {
            String currentText = messageTextView.getText() != null ? messageTextView.getText().toString() : "";
            messageTextView.setText(currentText + (currentText.isEmpty() ? "" : "\n") + data);
            Log.d(Constants.TAG, "Updated TextView with data: " + data);
        } else {
            Log.d(Constants.TAG, "messageTextView is null or data is null. TextView: " + (messageTextView != null) + ", data: " + data);
        } /*else {
            Intent startedServiceActivityIntent = new Intent(context, StartedServiceActivity.class);
            startedServiceActivityIntent.putExtra(Constants.MESSAGE, data);
            startedServiceActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(startedServiceActivityIntent);
        }*/

        // TODO: exercise 10 - restart the activity through an intent
        // if the messageTextView is not available
    }
}
