package com.example.colocviumarti;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    final private static int ACTIVITY_REQUEST_CODE = 2017;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckBox cb1 = findViewById(R.id.check_box1);
        CheckBox cb2 = findViewById(R.id.check_box2);

        Button check = findViewById(R.id.button1);
        Button run = findViewById(R.id.button2);

        EditText text1 = findViewById(R.id.text_1);
        EditText text2 = findViewById(R.id.text_2);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer finalStr = new StringBuffer();

                if (cb1.isChecked())
                    finalStr.append(cb1.getText().toString());

                if (cb2.isChecked())
                    finalStr.append(cb2.getText().toString());

                text1.setText(finalStr);
            }
        });

        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = text1.getText().toString() + text2.getText().toString();
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("strings", text);

                startActivity(intent);
            }
        });
    }
}
