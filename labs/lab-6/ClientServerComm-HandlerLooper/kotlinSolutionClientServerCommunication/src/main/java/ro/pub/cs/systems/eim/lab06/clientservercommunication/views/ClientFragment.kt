package ro.pub.cs.systems.eim.lab06.clientservercommunication.views

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import ro.pub.cs.systems.eim.lab06.clientservercommunication.R
import ro.pub.cs.systems.eim.lab06.clientservercommunication.network.ClientAsyncTask

class ClientFragment : Fragment() {
    private var serverAddressEditText: EditText? = null
    private var serverPortEditText: EditText? = null
    private var serverMessageTextView: TextView? = null
    private var displayMessageButton: Button? = null

    private val buttonClickListener = ButtonClickListener()

    private inner class ButtonClickListener : View.OnClickListener {
        override fun onClick(view: View?) {
            val clientAsyncTask = ClientAsyncTask(serverMessageTextView!!)
            clientAsyncTask.execute(
                serverAddressEditText!!.text.toString(),
                serverPortEditText!!.text.toString()
            )
        }
    }

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, state: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_client, parent, false)
        serverAddressEditText = view.findViewById(R.id.server_address_edit_text)
        serverPortEditText = view.findViewById(R.id.server_port_edit_text)
        displayMessageButton = view.findViewById(R.id.display_message_button)
        displayMessageButton!!.setOnClickListener(buttonClickListener)
        serverMessageTextView = view.findViewById(R.id.server_message_text_view)
        return view
    }
}
