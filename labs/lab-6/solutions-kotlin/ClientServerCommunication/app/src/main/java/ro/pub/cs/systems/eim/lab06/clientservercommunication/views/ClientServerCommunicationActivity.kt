package ro.pub.cs.systems.eim.lab06.clientservercommunication.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ro.pub.cs.systems.eim.lab06.clientservercommunication.R

class ClientServerCommunicationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_server_communication)

        val fragmentManager = getFragmentManager()
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.server_frame_layout, ServerFragment())
        fragmentTransaction.add(R.id.client_frame_layout, ClientFragment())
        fragmentTransaction.commit()
    }
}
