package com.example.passwordmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddAccountForm extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] socialMedia={"Facebook","Gmail","Yahoo","Instagram","Linkedin", "Github","Microsoft","Others"};
    Button button,addAccount;
    EditText username, email,password;
    Spinner socialmedia1;
    Spinner spin;
    Bundle bi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account_form);
        setTitle("Add Account");
        spin = findViewById(R.id.socialNetwork);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,socialMedia);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        Intent i = getIntent();
        bi = i.getExtras();
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        socialmedia1 = findViewById(R.id.socialNetwork);
        button = findViewById(R.id.button_button);
        addAccount = findViewById(R.id.deleteButton);
        String username1 = (String)bi.getString("USERNAME").toString();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db= new DatabaseHelper(AddAccountForm.this);
                AccountsModel am = new AccountsModel(-1,username1,username.getText().toString(),email.getText().toString(),password.getText().toString(),socialmedia1.getSelectedItem().toString());
                boolean success = db.addAccount(am);
                finish();

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}