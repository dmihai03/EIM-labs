package ro.pub.cs.systems.eim.lab06.clientservercommunication.network;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

import ro.pub.cs.systems.eim.lab06.clientservercommunication.general.Constants;
import ro.pub.cs.systems.eim.lab06.clientservercommunication.general.Utilities;

public class ClientAsyncTask {

    private final TextView serverMessageTextView;
    // - declare a Handler field named mainHandler
    private Handler mainHandler;

    public ClientAsyncTask(TextView serverMessageTextView) {
        this.serverMessageTextView = serverMessageTextView;
        // TODO
        // - initialize executorService with Executors.newSingleThreadExecutor()
        // executorService = ...
        // - initialize mainHandler with new Handler(Looper.getMainLooper())
        // mainHandler = ...
    }

    public void execute(String serverAddress, String serverPort) {
        // TODO
        // - clear the content of serverMessageTextView on the UI thread
        //   using mainHandler.post() with a lambda that calls setText("")
        // Example: mainHandler.post(() -> serverMessageTextView.setText(""));

        // TODO
        // - execute the network operation on a background thread using executorService.execute()
        //   Inside the lambda:
        //   - declare a Socket variable initialized to null
        //   - in a try block:
        //     * parse serverPort to int using Integer.parseInt()
        //     * create a new Socket with serverAddress and port
        //     * log the connection opened (use Constants.TAG and Log.v)
        //     * get a BufferedReader using Utilities.getReader(socket)
        //     * while the line read is not null:
        //       - capture the current line in a final variable (String line = currentLine)
        //       - post to mainHandler to append the line + "\n" to serverMessageTextView
        //   - in a catch block for IOException:
        //     * log the exception (use Constants.TAG and Log.e)
        //   - in a finally block:
        //     * try to close the socket if it's not null
        //     * log "Connection closed" (use Constants.TAG and Log.v)
        //     * catch IOException and log it
        // Example structure:
        // executorService.execute(() -> {
        //     Socket socket = null;
        //     try {
        //         // TODO: implement socket connection and reading
        //     } catch (IOException ioException) {
        //         // TODO: handle exception
        //     } finally {
        //         // TODO: close socket
        //     }
        // });
    }
}
