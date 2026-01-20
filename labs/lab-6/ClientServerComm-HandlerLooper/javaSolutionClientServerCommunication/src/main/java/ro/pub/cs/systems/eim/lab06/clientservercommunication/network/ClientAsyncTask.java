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
    private final Handler mainHandler;

    public ClientAsyncTask(TextView serverMessageTextView) {
        this.serverMessageTextView = serverMessageTextView;
        this.mainHandler = new Handler(Looper.getMainLooper());
    }

    public void execute(String serverAddress, String serverPort) {
        // Clear text view on UI thread
        mainHandler.post(() -> serverMessageTextView.setText(""));

        // Execute network operation on a new background thread
        new Thread(() -> {
            Socket socket = null;
            try {
                int port = Integer.parseInt(serverPort);
                socket = new Socket(serverAddress, port);
                Log.v(Constants.TAG, "Connection opened with " + socket.getInetAddress() + ":" + socket.getLocalPort());
                BufferedReader bufferedReader = Utilities.getReader(socket);
                String currentLine;
                while ((currentLine = bufferedReader.readLine()) != null) {
                    final String line = currentLine;
                    // Update UI on main thread
                    mainHandler.post(() -> serverMessageTextView.append(line + "\n"));
                }
            } catch (IOException ioException) {
                Log.e(Constants.TAG, "An exception has occurred: " + ioException.getMessage());
            } finally {
                try {
                    if (socket != null) {
                        socket.close();
                    }
                    Log.v(Constants.TAG, "Connection closed");
                } catch (IOException ioException) {
                    Log.e(Constants.TAG, "An exception has occurred: " + ioException.getMessage());
                }
            }
        }).start(); // Start the thread
    }
}
