package com.example.kvantproject;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//import com.example.kvantproject.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class reg extends AppCompatActivity{
    EditText edit_email1, edit_password1;

    Intent intent;

    private List<User> listData;

    public static final String PREFS_NAME = "MyPrefsLogs";
    public static final String PREFS_NAME2 = "MyPrefsLogin";
    public SharedPreferences MyPrefsLogin;
    public static final String APP_PREFERENCES = "MyPrefsLogin";

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg);

        Button btnAdd = findViewById(R.id.registr);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(reg.this, reg2.class);
                startActivity(intent);
            }
        });

        edit_email1 = findViewById(R.id.edit_email1);
        edit_password1 = findViewById(R.id.edit_password1);

        Button btnRead = findViewById(R.id.button_login);

        listData = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();

        intent = new Intent(reg.this, MainActivity.class);

        SharedPreferences settings = getSharedPreferences(reg.PREFS_NAME, 0);
        //Get "hasLoggedIn" value. If the value doesn't exist yet false is returned

        if(settings.getBoolean("hasLoggedIn", false))
        {
            startActivity(intent);
        }

    }


    public void onClickSignIn(View view)
    {

        String email = edit_email1.getText().toString();
        String password = edit_password1.getText().toString();
        if(!TextUtils.isEmpty(edit_email1.getText().toString()) && !TextUtils.isEmpty(edit_password1.getText().toString()))
        {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast toast = Toast.makeText(getApplicationContext(), "Вы успешно вошли", Toast.LENGTH_SHORT);
                        toast.show();
                        Intent intent2 = new Intent(reg.this, MainActivity.class);
                        startActivity(intent2);
                        //User has successfully logged in, save this information
                        // We need an Editor object to make preference changes.
                        SharedPreferences settings = getSharedPreferences(reg.PREFS_NAME, 0); // 0 - for private mode
                        SharedPreferences.Editor editor = settings.edit();
                        //Set "hasLoggedIn" to true
                        editor.putBoolean("hasLoggedIn", true);
                        // Commit the edits!
                        editor.apply();
                        SharedPreferences settings2 = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE); // 0 - for private mode
                        SharedPreferences.Editor editor2 = settings2.edit();
                        //Set "hasLoggedIn" to true
                        editor2.putString("Login",email);
                        editor2.apply();
                    }
                    else
                    {
                        Toast toast1 = Toast.makeText(getApplicationContext(), "Произошла ошибка при входе", Toast.LENGTH_SHORT);
                        toast1.show();
                    }
                }
            });
        }
        else
        {
            Toast toast2 = Toast.makeText(getApplicationContext(), "Введены пустые данные", Toast.LENGTH_SHORT);
            toast2.show();
        }
    }

}