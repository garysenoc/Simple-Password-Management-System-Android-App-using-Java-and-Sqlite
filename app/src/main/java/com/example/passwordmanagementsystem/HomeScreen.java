package com.example.passwordmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class HomeScreen extends AppCompatActivity {

    Bundle bi;
    ListView accountList;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Accounts");
        setContentView(R.layout.activity_home_screen);
        Intent i = getIntent();
        bi = i.getExtras();
        accountList = findViewById(R.id.accountList);

        username = (String)bi.getString("USERNAME").toString();

        DatabaseHelper db = new DatabaseHelper(HomeScreen.this);
        List<AccountsModel> tanan = db.getEveryOne(username);
        ArrayAdapter ad = new ArrayAdapter<AccountsModel>(HomeScreen.this, android.R.layout.simple_list_item_1,tanan);
        accountList.setAdapter(ad);

        Toast.makeText(getApplicationContext(),tanan.toString(),Toast.LENGTH_LONG);

        accountList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String as = tanan.get(position).toString();
                String[] spl = as.split("=",0);
                char a[] = spl[1].toCharArray();
                String myStr = Character.toString(a[0]);

                StringBuilder st = new StringBuilder();

                st.append(myStr);

                for(int i = 1;i<a.length;i++){
                    if(Character.isDigit(a[i])){
                        st.append(a[1]);
                    }else{
                        break;
                    }
                }



                Intent intent = new Intent(getApplicationContext(),UpdateDeleteForm.class);
                Bundle bundle = new Bundle();
                bundle.putInt("accountID",Integer.parseInt(st.toString()));
                bundle.putString("USERNAME",username);
                intent.putExtras(bundle);
                startActivity(intent,bundle);

            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
        //return super.onCreateOptionsMenu(menu);  
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addAccount) {
            Intent intent = new Intent(getApplicationContext(), AddAccountForm.class);
            Bundle bundle = new Bundle();
            bundle.putString("USERNAME",username);
            intent.putExtras(bundle);

            startActivity(intent,bundle);
        }
        if(item.getItemId() == R.id.refreshButton){
            DatabaseHelper db = new DatabaseHelper(HomeScreen.this);
            List<AccountsModel> tanan = db.getEveryOne(username);
            ArrayAdapter ad = new ArrayAdapter<AccountsModel>(HomeScreen.this, android.R.layout.simple_list_item_1,tanan);
            accountList.setAdapter(ad);

        }

        if (item.getItemId() == R.id.logoutButton) {
            finish();
        }

        return true;
//        return super.onOptionsItemSelected(item);
    }


}