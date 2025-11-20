package ro.pub.cs.systems.eim.bluetoothchatapp;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private BluetoothAdapter bluetoothAdapter;
    private ArrayAdapter<String> chatArrayAdapter;
    private List<String> chatMessages;

    private BluetoothDevice selectedDevice;

    private EditText messageEditText;

    private AcceptThread acceptThread;
    private ConnectThread connectThread;
    private ConnectedThread connectedThread;

    protected static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private final int REQUEST_ENABLE_BT = 1;
    private final int REQUEST_PERMISSIONS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO 1: Initialize UI components
        // - Find views by ID: chatListView, messageEditText, sendButton, listDevicesButton
        // - Initialize chatMessages list and chatArrayAdapter
        // - Set adapter to chatListView

        // TODO 2: Initialize Bluetooth adapter
        // - Get default Bluetooth adapter
        // - Check if Bluetooth is supported (if null, show toast and finish)

        // TODO 3: Request necessary permissions
        // - Call requestPermissions() method

        // TODO 4: Start server socket to listen for incoming connections
        // - Create and start AcceptThread

        // TODO 5: Set up button listeners
        // - listDevicesButton should call listPairedDevices()
        // - sendButton should get message from EditText, send it via connectedThread,
        //   clear EditText, and add message to chat
    }

    public void addChatMessage(String message) {
        chatMessages.add(message);
        chatArrayAdapter.notifyDataSetChanged();
    }

    private void requestPermissions() {
        List<String> permissions = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            permissions.add(Manifest.permission.BLUETOOTH_CONNECT);
            permissions.add(Manifest.permission.BLUETOOTH_SCAN);
        } else {
            permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        ActivityCompat.requestPermissions(this, permissions.toArray(new String[0]), REQUEST_PERMISSIONS);
    }

    // TODO 6: Implement the method that displays a dialog for selecting a paired device
    private void listPairedDevices() {
        // - Check BLUETOOTH_CONNECT permission
        // - Get bonded devices from bluetoothAdapter
        // - Create lists for device names and device objects
        // - Build AlertDialog with device list
        // - On item click: save selectedDevice and start ConnectThread
    }

    public void manageConnectedSocket(BluetoothSocket socket) {
        if (connectedThread != null) {
            connectedThread.cancel();
        }
        connectedThread = new ConnectedThread(this, socket);
        connectedThread.start();
    }

    // TODO 7: Handle runtime permission results
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // - Check if requestCode matches REQUEST_PERMISSIONS
        // - Verify all permissions were granted
        // - If not granted, show toast and finish
        // - If Bluetooth is not enabled, request to enable it via Intent
    }

    // TODO 8: Handle Bluetooth enable result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // - Check if requestCode is REQUEST_ENABLE_BT
        // - If result is not OK, show toast and finish
        super.onActivityResult(requestCode, resultCode, data);
    }

    // TODO 9: Clean up threads on activity destroy
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // - Cancel acceptThread, connectThread, and connectedThread if they exist
    }
}
