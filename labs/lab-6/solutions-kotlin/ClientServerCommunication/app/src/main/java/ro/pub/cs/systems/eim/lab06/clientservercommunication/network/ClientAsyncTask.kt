package ro.pub.cs.systems.eim.lab06.clientservercommunication.network

import android.os.AsyncTask
import android.util.Log
import android.widget.TextView
import ro.pub.cs.systems.eim.lab06.clientservercommunication.general.Constants
import ro.pub.cs.systems.eim.lab06.clientservercommunication.general.Utilities.getReader
import java.io.IOException
import java.net.Socket

class ClientAsyncTask(private val serverMessageTextView: TextView) :
    AsyncTask<String?, String?, Void?>() {
    override fun doInBackground(vararg params: String?): Void? {
        var socket: Socket? = null
        try {
            val serverAddress: String? = params[0]
            val serverPort = params[1]!!.toInt()
            socket = Socket(serverAddress, serverPort)
            Log.v(
                Constants.TAG,
                "Connection opened with " + socket.getInetAddress() + ":" + socket.getLocalPort()
            )
            val bufferedReader = getReader(socket)
            var currentLine: String?
            while ((bufferedReader.readLine().also { currentLine = it }) != null) {
                publishProgress(currentLine)
            }
        } catch (ioException: IOException) {
            Log.e(Constants.TAG, "An exception has occurred: " + ioException.message)
        } finally {
            try {
                if (socket != null) {
                    socket.close()
                }
                Log.v(Constants.TAG, "Connection closed")
            } catch (ioException: IOException) {
                Log.e(Constants.TAG, "An exception has occurred: " + ioException.message)
            }
        }
        return null
    }

    override fun onPreExecute() {
        serverMessageTextView.setText("")
    }

    override fun onProgressUpdate(vararg progress: String?) {
        serverMessageTextView.append(progress[0] + "\n")
    }

    override fun onPostExecute(result: Void?) {}
}
