package com.example.colocviumarti;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intentFromParent = getIntent();
        Bundle data = intentFromParent.getExtras();

        EditText text = findViewById(R.id.child_text);

        text.setText(data.getString("strings"));

        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    sendBroadcast(intentFromParent);
                    Log.d("[BCAST]", "run: sent broadcast");
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        Log.d("Main Activity2", "SLEEEP WROOOOOR");
                    }
                }
            }
        };
        thread.start();
    }
}
