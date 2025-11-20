package com.example.phonedialer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

class Constants {
    public static final int PERMISSION_REQUEST_CALL_PHONE = 1;
    public static final int CONTACTS_MANAGER_REQUEST_CODE = 7998;
}

public class MainActivity extends AppCompatActivity {
    private final ArrayList<Button> buttons = new ArrayList<Button>();
    private TextView textView;
    private int digitsCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.input_text);

        buttons.add(findViewById(R.id.button_0));
        buttons.add(findViewById(R.id.button_1));
        buttons.add(findViewById(R.id.button_2));
        buttons.add(findViewById(R.id.button_3));
        buttons.add(findViewById(R.id.button_4));
        buttons.add(findViewById(R.id.button_5));
        buttons.add(findViewById(R.id.button_6));
        buttons.add(findViewById(R.id.button_7));
        buttons.add(findViewById(R.id.button_8));
        buttons.add(findViewById(R.id.button_9));

        for (Button button : buttons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (digitsCount == 4 || digitsCount == 7)
                        textView.append(" ");
                    if (digitsCount < 15) {
                        digitsCount++;
                        textView.append(button.getText().toString());
                    }
                }
            });
        }

        ImageButton erase = (ImageButton)findViewById(R.id.erase_button);
        erase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                digitsCount--;
                textView.setText(textView.getText().toString().toCharArray(), 0, textView.getText().length() - 1);
            }
        });

        Button starButton = (Button)findViewById(R.id.button_star);
        starButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (digitsCount == 4 || digitsCount == 7)
                    textView.append(" ");
                if (digitsCount < 15) {
                    digitsCount++;
                    textView.append(starButton.getText().toString());
                }
            }
        });

        Button hashtagButton = (Button)findViewById(R.id.button_hashtag);
        hashtagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (digitsCount == 4 || digitsCount == 7)
                    textView.append(" ");
                if (digitsCount < 15) {
                    digitsCount++;
                    textView.append(hashtagButton.getText().toString());
                }
            }
        });

        ImageButton hangupButton = (ImageButton)findViewById(R.id.hangup);
        hangupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageButton callButton = (ImageButton)findViewById(R.id.call);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            MainActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            Constants.PERMISSION_REQUEST_CALL_PHONE);
                } else {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + textView.getText().toString()));
                    startActivity(intent);
                }
            }
        });

        ImageButton saveButton = (ImageButton)findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = textView.getText().toString();
                if (phoneNumber.length() > 0) {
                    Intent intent = new Intent("ro.pub.cs.systems.eim.lab04.contactsmanager.intent.action.ContactsManagerActivity");
                    intent.putExtra("ro.pub.cs.systems.eim.lab04.contactsmanager.PHONE_NUMBER_KEY", phoneNumber);
                    startActivityForResult(intent, Constants.CONTACTS_MANAGER_REQUEST_CODE);
                } else {
                    Toast.makeText(getApplication(), getResources().getString(R.string.phone_error), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
