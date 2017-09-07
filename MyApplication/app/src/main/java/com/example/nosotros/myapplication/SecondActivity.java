package com.example.nosotros.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private Button backBtn;
    private TextView msgText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        backBtn = (Button) findViewById(R.id.buttonBack);
        msgText = (TextView) findViewById(R.id.textSecond);

        Bundle actBundle = getIntent().getExtras();
        if(actBundle != null)
        {
            msgText.setText(actBundle.getString("message"));
        }
        else
        {
            Toast.makeText(SecondActivity.this, "Bundle empty", Toast.LENGTH_LONG);
        }

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Texto de muestra", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
    }
}
