package ro.pub.cs.systems.eim.lab05.startedserviceactivity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StartedServiceActivity : AppCompatActivity() {

    private lateinit var messageTextView: TextView
    private lateinit var startedServiceBroadcastReceiver: StartedServiceBroadcastReceiver
    private lateinit var startedServiceIntentFilter: IntentFilter
    private var serviceStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_started_service)

        messageTextView = findViewById(R.id.message_text_view)
        messageTextView.movementMethod = ScrollingMovementMethod()

        // TODO: exercise 8a - create an instance of the StartedServiceBroadcastReceiver broadcast receiver
        startedServiceBroadcastReceiver = StartedServiceBroadcastReceiver(messageTextView)

        // TODO: exercise 8b - create an instance of an IntentFilter
        // with all available actions contained within the broadcast intents sent by the service
        startedServiceIntentFilter = IntentFilter().apply {
            addAction(Constants.ACTION_STRING)
            addAction(Constants.ACTION_INTEGER)
            addAction(Constants.ACTION_ARRAY_LIST)
        }

        // TODO: exercise 6 - start the service (moved to onResume to start after registering receiver)
    }

    override fun onResume() {
        super.onResume()

        // TODO: exercise 8c - register the broadcast receiver with the corresponding intent filter
        // Register with RECEIVER_EXPORTED for cross-app broadcasts
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(startedServiceBroadcastReceiver, startedServiceIntentFilter, Context.RECEIVER_EXPORTED)
        } else {
            @Suppress("DEPRECATION")
            registerReceiver(startedServiceBroadcastReceiver, startedServiceIntentFilter)
        }

        // TODO: exercise 6 - start the service AFTER registering the receiver
        if (!serviceStarted) {
            val intent = Intent().apply {
                component = ComponentName(
                    "ro.pub.cs.systems.eim.lab05.startedservice",
                    "ro.pub.cs.systems.eim.lab05.startedservice.StartedService"
                )
            }
            startForegroundService(intent)
            serviceStarted = true
        }
    }

    override fun onPause() {
        // TODO: exercise 8c - unregister the broadcast receiver
        unregisterReceiver(startedServiceBroadcastReceiver)

        super.onPause()
    }

    override fun onDestroy() {
        // TODO: exercise 8d - stop the service
        val intent = Intent().apply {
            component = ComponentName(
                "ro.pub.cs.systems.eim.lab05.startedservice",
                "ro.pub.cs.systems.eim.lab05.startedservice.StartedService"
            )
        }
        stopService(intent)

        super.onDestroy()
    }

    // TODO: exercise 9 - implement the onNewIntent callback method
    // get the message from the extra field of the intent
    // and display it in the messageTextView

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val message = intent.getStringExtra(Constants.MESSAGE)
        message?.let {
            val currentText = messageTextView.text?.toString() ?: ""
            messageTextView.text = currentText + (if (currentText.isEmpty()) "" else "\n") + it
        }
    }
}
