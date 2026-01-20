package ro.pub.cs.systems.eim.lab06.clientservercommunication.network

import android.util.Log
import android.widget.EditText
import ro.pub.cs.systems.eim.lab06.clientservercommunication.general.Constants
import java.io.IOException
import java.net.InetAddress
import java.net.ServerSocket

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
            serverSocket!!.close()
        } catch (ioException: IOException) {
            Log.e(Constants.TAG, "An exception has occurred: " + ioException.message)
        }
        Log.v(Constants.TAG, "stopServer() method was invoked")
    }

    override fun run() {
        try {
            serverSocket = ServerSocket(Constants.SERVER_PORT, 50, InetAddress.getByName("0.0.0.0"))
            while (isRunning) {
                val socket = serverSocket!!.accept()
                Log.v(Constants.TAG, "accept()-ed: " + socket.getInetAddress())
                val communicationThread = CommunicationThread(socket, serverTextEditText)
                communicationThread.start()
            }
        } catch (ioException: IOException) {
            Log.e(Constants.TAG, "An exception has occurred: " + ioException.message)
        }
    }
}
