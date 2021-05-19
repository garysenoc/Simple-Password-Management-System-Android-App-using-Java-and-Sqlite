package com.example.passwordmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterForm extends AppCompatActivity {
    EditText fullname,username,password;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);
        fullname = findViewById(R.id.username);
        username = findViewById(R.id.email);
        password = findViewById(R.id.password);
        registerButton = findViewById(R.id.registerButton);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerAccount(username.getText().toString());
            }
        });


    }

    public void gotoLogin(View v){
        finish();
    }

    public void registerAccount(String username1){
        DatabaseHelper db = new DatabaseHelper(RegisterForm.this);


        if(fullname.getText().toString().isEmpty() || username.getText().toString().isEmpty()||password.getText().toString().isEmpty()){
            Toast.makeText(RegisterForm.this,"Please complete the needed requirements", Toast.LENGTH_LONG).show();
        }
        else if(db.checkExistingUser(username1)==true){
            Toast.makeText(RegisterForm.this,"Username is already taken. Try another one.", Toast.LENGTH_LONG).show();
        }
        else{
            UserModel usermodel = new UserModel(-1, username.getText().toString(), fullname.getText().toString(), password.getText().toString());
            db.addUser(usermodel);
            gotoLogin(null);
        }
    }


}