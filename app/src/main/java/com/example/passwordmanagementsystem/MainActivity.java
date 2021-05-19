package com.example.passwordmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button buttonLogin;
    EditText username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLogin = findViewById(R.id.loginButton);
        username = findViewById(R.id.textUsername);
        password = findViewById(R.id.et_password);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(MainActivity.this);
                if(username.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"Fill up both username and password.", Toast.LENGTH_LONG).show();
                }
                else if(db.loginUser(username.getText().toString(),password.getText().toString())==true)
                {
                    Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("USERNAME",username.getText().toString());

                    intent.putExtras(bundle);
                    startActivity(intent,bundle);
                }
                else{
                    Toast.makeText(MainActivity.this,"Invalid Username / Password", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    public void gotoRegister(View v){
        Intent intent = new Intent(getApplicationContext(), RegisterForm.class);
        startActivity(intent);
    }

    public void gotoHome(View v){
        Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
        startActivity(intent);
    }

}