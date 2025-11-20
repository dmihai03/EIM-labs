package ro.pub.cs.systems.eim.lab05.startedserviceactivity

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StartedServiceActivity : AppCompatActivity() {

    private lateinit var messageTextView: TextView
    private lateinit var startedServiceBroadcastReceiver: StartedServiceBroadcastReceiver
    private lateinit var startedServiceIntentFilter: IntentFilter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_started_service)

        messageTextView = findViewById(R.id.message_text_view)

        // TODO: exercise 6 - start the service

        // TODO: exercise 8a - create an instance of the StartedServiceBroadcastReceiver broadcast receiver

        // TODO: exercise 8b - create an instance of an IntentFilter
        // with all available actions contained within the broadcast intents sent by the service

    }

    override fun onResume() {
        super.onResume()

        // TODO: exercise 8c - register the broadcast receiver with the corresponding intent filter
        // Note: RECEIVER_NOT_EXPORTED is required for Android 13+ (API 33+)
        if (::startedServiceBroadcastReceiver.isInitialized && ::startedServiceIntentFilter.isInitialized) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                registerReceiver(startedServiceBroadcastReceiver, startedServiceIntentFilter, Context.RECEIVER_NOT_EXPORTED)
            }
        }
    }

    override fun onPause() {
        // TODO: exercise 8c - unregister the broadcast receiver
        if (::startedServiceBroadcastReceiver.isInitialized) {
            unregisterReceiver(startedServiceBroadcastReceiver)
        }

        super.onPause()
    }

    override fun onDestroy() {
        // TODO: exercise 8d - stop the service

        super.onDestroy()
    }

    // TODO: exercise 9 - implement the onNewIntent callback method
    // get the message from the extra field of the intent
    // and display it in the messageTextView

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        // Assuming "extraMessage" is the key for the message in the intent extras
        val message = intent.getStringExtra("extraMessage")
        message?.let {
            messageTextView.text = it
        }
    }
}
