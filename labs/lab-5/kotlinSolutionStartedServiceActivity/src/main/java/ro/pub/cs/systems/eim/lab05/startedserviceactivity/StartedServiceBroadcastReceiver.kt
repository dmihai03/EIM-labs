package ro.pub.cs.systems.eim.lab05.startedserviceactivity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.TextView

class StartedServiceBroadcastReceiver(private val messageTextView: TextView?) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // TODO: exercise 7 - get the action and the extra information from the intent
        // and set the text on the messageTextView
        val action = intent.action
        Log.d(Constants.TAG, "onReceive called with action: $action")
        
        val data: String? = when (action) {
            Constants.ACTION_STRING -> intent.getStringExtra(Constants.DATA)
            Constants.ACTION_INTEGER -> intent.getIntExtra(Constants.DATA, -1).toString()
            Constants.ACTION_ARRAY_LIST -> {
                val arrayList = intent.getStringArrayListExtra(Constants.DATA)
                arrayList?.toString()
            }
            else -> null
        }
        
        if (messageTextView != null && data != null) {
            val currentText = messageTextView.text?.toString() ?: ""
            messageTextView.text = currentText + (if (currentText.isEmpty()) "" else "\n") + data
            Log.d(Constants.TAG, "Updated TextView with data: $data")
        } else {
            Log.d(Constants.TAG, "messageTextView is null or data is null. TextView: ${messageTextView != null}, data: $data")
        }

        // TODO: exercise 9 - restart the activity through an intent
        // if the messageTextView is not available
        // (Currently commented out - uncomment if needed)
        /*if (messageTextView == null && data != null) {
            val startedServiceActivityIntent = Intent(context, StartedServiceActivity::class.java)
            startedServiceActivityIntent.putExtra(Constants.MESSAGE, data)
            startedServiceActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            context.startActivity(startedServiceActivityIntent)
        }*/
    }
}
