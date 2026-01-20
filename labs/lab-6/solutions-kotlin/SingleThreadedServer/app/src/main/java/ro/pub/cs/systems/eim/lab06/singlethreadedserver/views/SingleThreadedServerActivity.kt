package ro.pub.cs.systems.eim.lab06.singlethreadedserver.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import ro.pub.cs.systems.eim.lab06.singlethreadedserver.R
import ro.pub.cs.systems.eim.lab06.singlethreadedserver.general.Constants
import ro.pub.cs.systems.eim.lab06.singlethreadedserver.network.ServerThread

class SingleThreadedServerActivity : AppCompatActivity() {
    private var serverTextEditText: EditText? = null

    private var serverThread: ServerThread? = null

    private val serverTextContentWatcher = ServerTextContentWatcher()

    private inner class ServerTextContentWatcher : TextWatcher {
        override fun beforeTextChanged(
            charSequence: CharSequence?,
            start: Int,
            count: Int,
            after: Int
        ) {
        }

        override fun onTextChanged(
            charSequence: CharSequence,
            start: Int,
            before: Int,
            count: Int
        ) {
            Log.v(Constants.TAG, "Text changed in edit text: " + charSequence.toString())
            if (Constants.SERVER_START == charSequence.toString()) {
                serverTextEditText?.let { serverThread = ServerThread(it) }
                serverThread!!.startServer()
                Log.v(Constants.TAG, "Starting server...")
            }
            if (Constants.SERVER_STOP == charSequence.toString()) {
                serverThread!!.stopServer()
                Log.v(Constants.TAG, "Stopping server...")
            }
        }

        override fun afterTextChanged(editable: Editable?) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_threaded_server)

        serverTextEditText = findViewById (R.id.server_text_edit_text) as EditText
        serverTextEditText!!.addTextChangedListener(serverTextContentWatcher)
    }

    override fun onDestroy() {
        if (serverThread != null) {
            serverThread!!.stopServer()
        }
        super.onDestroy()
    }
}
