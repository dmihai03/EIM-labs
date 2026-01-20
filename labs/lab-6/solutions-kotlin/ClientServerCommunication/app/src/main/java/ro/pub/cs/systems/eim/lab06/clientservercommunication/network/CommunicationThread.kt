package ro.pub.cs.systems.eim.lab06.clientservercommunication.network

import android.util.Log
import android.widget.EditText
import ro.pub.cs.systems.eim.lab06.clientservercommunication.general.Constants
import ro.pub.cs.systems.eim.lab06.clientservercommunication.general.Utilities.getWriter
import java.io.IOException
import java.net.Socket

class CommunicationThread(private val socket: Socket, private val serverTextEditText: EditText) :
    Thread() {
    override fun run() {
        try {
            Log.v(
                Constants.TAG,
                "Connection opened to " + socket.getLocalAddress() + ":" + socket.getLocalPort() + " from " + socket.getInetAddress()
            )
            val printWriter = getWriter(socket)
            printWriter.println(serverTextEditText.getText().toString())
            socket.close()
            Log.v(Constants.TAG, "Connection closed")
        } catch (ioException: IOException) {
            Log.e(Constants.TAG, "An exception has occurred: " + ioException.message)
        }
    }
}
