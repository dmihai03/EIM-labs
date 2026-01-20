package ro.pub.cs.systems.eim.lab06.singlethreadedserver.network

import android.util.Log
import android.widget.EditText
import ro.pub.cs.systems.eim.lab06.singlethreadedserver.general.Constants
import ro.pub.cs.systems.eim.lab06.singlethreadedserver.general.Utilities.getWriter
import java.io.IOException
import java.net.ServerSocket

class ServerThread(private val serverTextEditText: EditText) : Thread() {
    private var isRunning = false

    private var serverSocket: ServerSocket? = null

    fun startServer() {
        isRunning = true
        start()
        Log.v(Constants.TAG, "startServer() method invoked " + serverSocket)
    }

    fun stopServer() {
        isRunning = false
        try {
            if (serverSocket != null) {
                serverSocket!!.close()
            }
        } catch (ioException: IOException) {
            Log.e(Constants.TAG, "An exception has occurred: " + ioException.message)
        }
        Log.v(Constants.TAG, "stopServer() method invoked")
    }

    override fun run() {
        try {
            serverSocket = ServerSocket(Constants.SERVER_PORT)
            while (isRunning) {
                val socket = serverSocket!!.accept()
                Log.v(
                    Constants.TAG,
                    "Connection opened with " + socket.getInetAddress() + ":" + socket.getLocalPort()
                )
                try {
                    sleep(3000)
                } catch (interruptedException: InterruptedException) {
                    Log.e(
                        Constants.TAG,
                        "An exception has occurred: " + interruptedException.message
                    )
                }
                val printWriter = getWriter(socket)
                printWriter.println(serverTextEditText.getText().toString())
                socket.close()
                Log.v(Constants.TAG, "Connection closed")
            }
        } catch (ioException: IOException) {
            Log.e(Constants.TAG, "An exception has occurred: " + ioException.message)
        }
    }
}
