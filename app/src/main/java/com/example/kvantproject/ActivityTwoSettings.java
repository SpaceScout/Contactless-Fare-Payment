package com.example.kvantproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class ActivityTwoSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_settings);

        Button changeTheme = findViewById(R.id.choose_theme);
        changeTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityTwoSettings.this, ChooseTheme.class);
                startActivity(intent);
            }
        });

        Button changeOplata = findViewById(R.id.changeOplata);
        changeOplata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityTwoSettings.this, ChangeOplata.class);
                startActivity(intent);
            }
        });
    }
}