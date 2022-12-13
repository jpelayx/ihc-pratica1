package com.example.exercicio1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText input_text1, input_text2;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_text1 = (TextInputEditText) findViewById(R.id.input_text1);
        input_text2 = (TextInputEditText) findViewById(R.id.input_text2);
        result = (TextView) findViewById(R.id.result);

    }

    public void somar(View view) {
        float sum;
        float val1 = Float.parseFloat(input_text1.getText().toString());
        float val2 = Float.parseFloat(input_text2.getText().toString());

        sum = val1 + val2;
        result.setText(Float.toString(sum));
    }
}