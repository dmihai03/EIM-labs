package ro.pub.cs.systems.eim.lab06.clientservercommunication.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import ro.pub.cs.systems.eim.lab06.clientservercommunication.R
import ro.pub.cs.systems.eim.lab06.clientservercommunication.general.Constants
import ro.pub.cs.systems.eim.lab06.clientservercommunication.network.ServerThread

class ServerFragment : Fragment() {

    private var serverEditText: EditText? = null
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
            Log.v(Constants.TAG, "Text changed in edit text: ${charSequence.toString()}")
            if (Constants.SERVER_START == charSequence.toString()) {
                serverThread = ServerThread(serverEditText!!)
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

    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        state: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_server, parent, false)
        serverEditText = view.findViewById(R.id.server_text_edit_text)
        serverEditText!!.addTextChangedListener(serverTextContentWatcher)
        return view
    }

    override fun onDestroy() {
        if (serverThread != null) {
            serverThread!!.stopServer()
        }
        super.onDestroy()
    }
}

