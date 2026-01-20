package ro.pub.cs.systems.eim.lab06.clientservercommunication.network

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.TextView
import ro.pub.cs.systems.eim.lab06.clientservercommunication.general.Constants
import ro.pub.cs.systems.eim.lab06.clientservercommunication.general.Utilities
import java.io.BufferedReader
import java.io.IOException
import java.net.Socket

class ClientAsyncTask(private val serverMessageTextView: TextView) {

    // TODO
    // - declare a Handler field named mainHandler
    private var mainHandler: Handler? = null

    init {
        // TODO
        // - initialize executorService with Executors.newSingleThreadExecutor()
        // executorService = ...
        // - initialize mainHandler with new Handler(Looper.getMainLooper())
        // mainHandler = ...
    }

    fun execute(serverAddress: String, serverPort: String) {
        // TODO
        // - clear the content of serverMessageTextView on the UI thread
        //   using mainHandler?.post() with a lambda that calls setText("")
        // Example: mainHandler?.post { serverMessageTextView.setText("") }

        // TODO 
        // - execute the network operation on a background thread using executorService?.execute()
        //   Inside the lambda:
        //   - declare a Socket variable initialized to null
        //   - in a try block:
        //     * parse serverPort to int using toInt()
        //     * create a new Socket with serverAddress and port
        //     * log the connection opened (use Constants.TAG and Log.v)
        //     * get a BufferedReader using Utilities.getReader(socket)
        //     * while the line read is not null:
        //       - capture the current line in a variable
        //       - post to mainHandler to append the line + "\n" to serverMessageTextView
        //   - in a catch block for IOException:
        //     * log the exception (use Constants.TAG and Log.e)
        //   - in a finally block:
        //     * try to close the socket if it's not null
        //     * log "Connection closed" (use Constants.TAG and Log.v)
        //     * catch IOException and log it
        // Example structure:
        // executorService?.execute {
        //     var socket: Socket? = null
        //     try {
        //         // TODO: implement socket connection and reading
        //     } catch (ioException: IOException) {
        //         // TODO: handle exception
        //     } finally {
        //         // TODO: close socket
        //     }
        // }
    }
}

