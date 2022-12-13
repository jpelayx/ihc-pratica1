package com.example.atividade2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView received_message = (TextView) findViewById(R.id.textView_received_message);
        String message = getIntent().getStringExtra("message");
        received_message.setText(message);
    }

}