package com.example.kvantproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseTheme extends AppCompatActivity {
    public static final String APP_PREFERENCES = "theme_prefs";
    final String KEY_RADIOBUTTON_INDEX = "SAVED_RADIO_BUTTON_INDEX";
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_theme);
        Toast toast1 = Toast.makeText(getApplicationContext(), "Выбрана светлая тема", Toast.LENGTH_LONG);
        Toast toast2 = Toast.makeText(getApplicationContext(), "Выбрана темная тема", Toast.LENGTH_LONG);
        //Button light_button = findViewById(R.id.wifiText);
        Button dark_button = findViewById(R.id.button2);

        //light_button.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View view) {
                //SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
                //toast1.show();
            }
        //});
        //dark_button.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View view) {

                //toast2.show();
            //}
        //});
    //}
}