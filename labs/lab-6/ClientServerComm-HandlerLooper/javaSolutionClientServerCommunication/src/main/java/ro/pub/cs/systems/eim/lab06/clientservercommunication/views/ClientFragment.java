package ro.pub.cs.systems.eim.lab06.clientservercommunication.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ro.pub.cs.systems.eim.lab06.clientservercommunication.R;
import ro.pub.cs.systems.eim.lab06.clientservercommunication.network.ClientAsyncTask;

public class ClientFragment extends Fragment {

    private EditText serverAddressEditText, serverPortEditText;
    private TextView serverMessageTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle state) {
        View view = inflater.inflate(R.layout.fragment_client, parent, false);
        serverAddressEditText = view.findViewById(R.id.server_address_edit_text);
        serverPortEditText = view.findViewById(R.id.server_port_edit_text);
        serverMessageTextView = view.findViewById(R.id.server_message_text_view);
        Button displayMessageButton = view.findViewById(R.id.display_message_button);

        displayMessageButton.setOnClickListener(view1 -> {
            ClientAsyncTask clientAsyncTask = new ClientAsyncTask(serverMessageTextView);
            clientAsyncTask.execute(serverAddressEditText.getText().toString(), serverPortEditText.getText().toString());
        });
        return view;
    }

}
