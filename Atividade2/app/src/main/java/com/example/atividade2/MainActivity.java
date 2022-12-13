package com.example.atividade2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText message_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message_text = (EditText) findViewById(R.id.editText_message);
    }

    public void send(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("message", message_text.getText().toString());
        startActivity(intent);
    }
}