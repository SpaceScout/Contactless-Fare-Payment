package com.example.kvantproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyAccount extends AppCompatActivity {

    private String USER_KEY = "User";
    private String uid;
    private String balanceStr, NewbalanceStr;
    private Integer balance;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);

        Button popolnit = findViewById(R.id.Popolnit);
        TextView user_email = findViewById(R.id.user_email);
        TextView user_balance = findViewById(R.id.user_balance);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        final FirebaseDatabase DatabaseReferences = FirebaseDatabase.getInstance();
        final DatabaseReference MyRefetance = DatabaseReferences.getReference(USER_KEY);

        if (currentUser != null) {
            String email = currentUser.getEmail();
            uid = currentUser.getUid();
            final DatabaseReference BalanceRef = MyRefetance.child(uid).child("balance");
            BalanceRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    balance = snapshot.getValue(Integer.class);
                    balanceStr = Integer.toString(balance);
                    user_email.setText("Email: " + email);
                    user_balance.setText("Balance: " + balanceStr);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Произошла ошибка", Toast.LENGTH_SHORT);
                    toast1.show();
                }
            });
        } else {
            Toast toast1 = Toast.makeText(getApplicationContext(), "Вы не авторизованы!", Toast.LENGTH_SHORT);
            toast1.show();
        }

        popolnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference BalanceRef = MyRefetance.child(uid).child("balance");
                balance = balance + 100;
                BalanceRef.setValue(balance);
                NewbalanceStr = Integer.toString(balance);
                user_balance.setText("Balance: " + NewbalanceStr);
            }
        });
    }
}
