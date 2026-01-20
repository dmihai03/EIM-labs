package ro.pub.cs.systems.eim.lab06.clientservercommunication.network

import android.util.Log
import android.widget.EditText
import ro.pub.cs.systems.eim.lab06.clientservercommunication.general.Constants
import java.io.IOException
import java.net.ServerSocket
import java.net.Socket

class ServerThread(private val serverTextEditText: EditText) : Thread() {

    private var isRunning = false
    private var serverSocket: ServerSocket? = null

    fun startServer() {
        isRunning = true
        start()
        Log.v(Constants.TAG, "startServer() method was invoked")
    }

    fun stopServer() {
        isRunning = false
        try {
            serverSocket?.close()
        } catch (ioException: IOException) {
            Log.e(Constants.TAG, "An exception has occurred: ${ioException.message}")
            if (Constants.DEBUG) {
                ioException.printStackTrace()
            }
        }
        Log.v(Constants.TAG, "stopServer() method was invoked")
    }

    override fun run() {
        try {
            serverSocket = ServerSocket(Constants.SERVER_PORT)
            while (isRunning) {
                val socket: Socket? = serverSocket?.accept()
                if (socket != null) {
                    val communicationThread = CommunicationThread(socket, serverTextEditText)
                    communicationThread.start()
                }
            }
        } catch (ioException: IOException) {
            Log.e(Constants.TAG, "An exception has occurred: ${ioException.message}")
            if (Constants.DEBUG) {
                ioException.printStackTrace()
            }
        }
    }
}

