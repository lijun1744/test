package com.example.administrator.tartb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TopBar topBar = findViewById(R.id.topBar);
        topBar.setOnClickListener(new TopBar.OnClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(MainActivity.this,"左边按下",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(MainActivity.this,"右边按下",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
