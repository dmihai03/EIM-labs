package ro.pub.cs.systems.eim.lab06.clientservercommunication.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import ro.pub.cs.systems.eim.lab06.clientservercommunication.R

class ClientServerCommunicationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_server_communication)

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.server_frame_layout, ServerFragment())
        fragmentTransaction.add(R.id.client_frame_layout, ClientFragment())
        fragmentTransaction.commit()
    }
}

