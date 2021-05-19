package com.example.passwordmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateDeleteForm extends AppCompatActivity {
    String[] socialMedia={"Facebook","Gmail","Yahoo","Instagram","Linkedin", "Github","Microsoft","Others"};
    Bundle bi;
    EditText username, email, password;
    Spinner spinner;
    Button buttonDelete,buttonUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_form);
        Intent i = getIntent();
        bi = i.getExtras();



        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        spinner = findViewById(R.id.socialNetwork);
        buttonDelete = findViewById(R.id.deleteButton);
        buttonUpdate = findViewById(R.id.updateButton);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,socialMedia);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);


        DatabaseHelper db = new DatabaseHelper(UpdateDeleteForm.this);
        AccountsModel mod = db.getOne((int)bi.getInt("accountID"),(String)bi.getString("USERNAME").toString());


        username.setText(mod.getUsername());
        email.setText(mod.getEmail());
        password.setText(mod.getPassword());
        setSpinText(spinner, mod.getSocialNetwork());

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(UpdateDeleteForm.this);
                db.deleteRecord(mod);
                Toast.makeText(getApplicationContext(),"You have successfully deleted an account.", Toast.LENGTH_LONG).show();
            finish();
            }
        });

      buttonUpdate.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              DatabaseHelper db = new DatabaseHelper(UpdateDeleteForm.this);

              AccountsModel ss = new AccountsModel(mod.getId(),(String)bi.getString("USERNAME").toString(),username.getText().toString(), email.getText().toString(),password.getText().toString(),spinner.getSelectedItem().toString());
              db.updateModel(ss);
              finish();
          }
      });

    }

    public void setSpinText(Spinner spin, String text)
    {
        for(int i= 0; i < spin.getAdapter().getCount(); i++)
        {
            if(spin.getAdapter().getItem(i).toString().contains(text))
            {
                spin.setSelection(i);
            }
        }

    }
}