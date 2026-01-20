package ro.pub.cs.systems.eim.lab06.clientservercommunication.network

import android.util.Log
import android.widget.EditText
import ro.pub.cs.systems.eim.lab06.clientservercommunication.general.Constants
import java.net.Socket

class CommunicationThread(
    private val socket: Socket,
    private val serverTextEditText: EditText
) : Thread() {

    override fun run() {
        try {
            Log.v(Constants.TAG, "Connection opened with ${socket.inetAddress}:${socket.localPort}")

            // TODO 
            // - get the PrintWriter object in order to write on the socket (use Utilities.getWriter())
            // - print a line containing the text in the serverTextEditText edit text

            socket.close()
            Log.v(Constants.TAG, "Connection closed")
        } catch (exception: Exception) {
            Log.e(Constants.TAG, "An exception has occurred: ${exception.message}")
            if (Constants.DEBUG) {
                exception.printStackTrace()
            }
        }
    }
}

