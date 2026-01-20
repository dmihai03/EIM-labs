package ro.pub.cs.systems.eim.lab06.clientservercommunication.views

import android.app.Fragment
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
                serverAddressEditText!!.getText().toString(),
                serverPortEditText!!.getText().toString()
            )
        }
    }

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, state: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_client, parent, false)
    }

    override fun onActivityCreated(state: Bundle?) {
        super.onActivityCreated(state)

        serverAddressEditText =
            getActivity().findViewById<View?>(R.id.server_address_edit_text) as EditText
        serverPortEditText =
            getActivity().findViewById<View?>(R.id.server_port_edit_text) as EditText
        displayMessageButton =
            getActivity().findViewById<View?>(R.id.display_message_button) as Button
        displayMessageButton!!.setOnClickListener(buttonClickListener)
        serverMessageTextView =
            getActivity().findViewById<View?>(R.id.server_message_text_view) as TextView
    }
}
