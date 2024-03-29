package com.example.kvantproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registr extends AppCompatActivity {
    private EditText edit_email, edit_password2;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDataBase;
    private DatabaseReference MyDatabaseReference;
    private String USER_KEY = "User";
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg);

        MyDatabaseReference = FirebaseDatabase.getInstance().getReference(USER_KEY);
        mAuth = FirebaseAuth.getInstance();

        Button zaregist = findViewById(R.id.zaregist);

        edit_email = findViewById(R.id.edit_email2);
        edit_password2 = findViewById(R.id.edit_password2);
    }


    public void onClickSignUp(View view)
    {
        String email = edit_email.getText().toString();
        String password = edit_password2.getText().toString();
        if(!TextUtils.isEmpty(edit_email.getText().toString()) && !TextUtils.isEmpty(edit_password2.getText().toString()))
        {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        currentUser = FirebaseAuth.getInstance().getCurrentUser();
                        String uid = currentUser.getUid();
                        User newUser = new User(
                                email,
                                password
                        );
                        MyDatabaseReference.child(uid).setValue(newUser);
                        Toast.makeText(getApplicationContext(), "Вы успешно зарегестрировались", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Registr.this, Login.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Произошла ошибка при регистрации", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
