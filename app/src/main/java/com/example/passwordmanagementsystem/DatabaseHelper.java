package com.example.passwordmanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String ID = "ID";
    public static final String CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";
    public static final String USERS = "USERS";
    public static final String USER_USERNAME = "USER_USERNAME";
    public static final String USER_FULLNAME = "USER_FULLNAME";
    public static final String USER_PASSWORD = "USER_PASSWORD";
    public static final String ACCOUNT_ACCOUNTNAME = "ACCOUNT_ACCOUNTNAME";
    public static final String ACCOUNTS = "ACCOUNTS";
    public static final String ACCOUNT_USERNAME = "ACCOUNT_USERNAME";
    public static final String ACCOUNT_EMAIL = "ACCOUNT_EMAIL";
    public static final String ACCOUNT_PASSWORD = "ACCOUNT_PASSWORD";
    public static final String ACCOUNT_SOCIALNETWORK = "ACCOUNT_SOCIALNETWORK"  ;

    public DatabaseHelper(@Nullable Context context) {
        super(context, "password_management.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //table creation
        String createTableUser = "CREATE TABLE " + USERS + "( ID INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_USERNAME + " TEXT, " + USER_FULLNAME + " TEXT, " + USER_PASSWORD + " TEXT)";

        String createTableAccount = "CREATE TABLE " + ACCOUNTS + "( ID INTEGER PRIMARY KEY AUTOINCREMENT, " + ACCOUNT_ACCOUNTNAME + " TEXT, " + ACCOUNT_USERNAME + " TEXT,  " + ACCOUNT_EMAIL + " TEXT, " + ACCOUNT_PASSWORD + " TEXT," + ACCOUNT_SOCIALNETWORK + " TEXT)";

        db.execSQL(createTableUser);
        db.execSQL(createTableAccount);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public boolean addUser(UserModel um){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_USERNAME, um.getUsername());
        cv.put(USER_FULLNAME, um.getFullname());
        cv.put(USER_PASSWORD, um.getPassword());

        long insert = db.insert(USERS, null,cv);

        return (insert == -1) ? false : true;
    }


    public boolean loginUser(String username, String password){
        String queryString = "SELECT * FROM USERS WHERE USER_USERNAME = '" + username + "' AND USER_PASSWORD = '" + password + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        return cursor.moveToFirst() == true ? true : false;
    }

    public boolean checkExistingUser(String username){
        String queryString = "SELECT * FROM USERS WHERE USER_USERNAME = '" + username + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        return cursor.moveToFirst() == true ? true : false;
    }


    public boolean addAccount(AccountsModel am){

//        private String accountName;
//        private String username;
//        private String email;
//        private String password;
//        private String socialNetwork;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ACCOUNT_ACCOUNTNAME, am.getAccountName());
        cv.put(ACCOUNT_USERNAME, am.getUsername());
        cv.put(ACCOUNT_EMAIL, am.getEmail());
        cv.put(ACCOUNT_PASSWORD, am.getPassword());
        cv.put(ACCOUNT_SOCIALNETWORK, am.getSocialNetwork());
        long insert = db.insert(ACCOUNTS, null,cv);

        return (insert == -1) ? false : true;
    }



    public List<AccountsModel> getEveryOne(String username){
        //        private String accountName;
//        private String username;
//        private String email;
//        private String password;
//        private String socialNetwork;
        List<AccountsModel> returnlist = new ArrayList<>();
        String  queryString = "SELECT * FROM " + ACCOUNTS + " where ACCOUNT_ACCOUNTNAME = '" + username +"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            do{
                int accountID = cursor.getInt(0);
                String accountName = cursor.getString(1);
                String accountUsername = cursor.getString(2);
                String email = cursor.getString(3);
                String password = cursor.getString(4);
                String socialNetwork = cursor.getString(5);
                AccountsModel cm = new AccountsModel(accountID,accountName,accountUsername,email,password,socialNetwork);

                returnlist.add(cm);
            }while(cursor.moveToNext());
        }else{
            cursor.close();
            db.close();
            return returnlist;
        }
        return returnlist;
    }


    public AccountsModel getOne(int id,String username) {
        String queryString = "SELECT * FROM " + ACCOUNTS + " where ACCOUNT_ACCOUNTNAME = '" + username + "' and ID = '" + id + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        AccountsModel cm = new AccountsModel();
        if (cursor.moveToFirst()) {
            do {
                int accountID = cursor.getInt(0);
                String accountName = cursor.getString(1);
                String accountUsername = cursor.getString(2);
                String email = cursor.getString(3);
                String password = cursor.getString(4);
                String socialNetwork = cursor.getString(5);
                AccountsModel dd = new AccountsModel(accountID, accountName, accountUsername, email, password, socialNetwork);
                return dd;
            } while (cursor.moveToNext());

        }
        else{
            return cm;
        }

    }



        public boolean deleteRecord (AccountsModel am){
        SQLiteDatabase db = this.getWritableDatabase();
        String qs = "DELETE FROM " + ACCOUNTS + " WHERE ID = " + am.getId();
        Cursor c = db.rawQuery(qs,null);
            if(c.moveToFirst())
                return true;
            else
                return false;
    }

    // implement the update'

    public boolean updateModel(AccountsModel am){

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(ACCOUNT_ACCOUNTNAME, am.getAccountName());
        cv.put(ACCOUNT_USERNAME, am.getUsername());
        cv.put(ACCOUNT_EMAIL, am.getEmail());
        cv.put(ACCOUNT_PASSWORD, am.getPassword());
        cv.put(ACCOUNT_SOCIALNETWORK, am.getSocialNetwork());

        db.update("ACCOUNTS",cv,"id=?",new String[]{String.valueOf(am.getId())});

//
//        db.execSQL("UPDATE "+ACCOUNTS+" SET ACCOUNT_ACCOUNTNAME ='" +am.getAccountName() +"'" + ", ACCOUNT_USERNAME ='"+ am.getUsername()+"', ACCOUNT_EMAIL = '"+am.getEmail()+"', ACCOUNT_PASSWORD= '"+am.getPassword()+"', ACCOUNT_SOCIALNETWORK='"+ am.getSocialNetwork()+"' where ID = " + accountID);

        return true;

    }
//
//    public boolean addOne(CustomerModel cm) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(CUSTOMER_NAME, cm.getName());
//        cv.put(CUSTOMER_AGE, cm.getAge());
//        cv.put(ACTIVE_CUSTOMER,cm.isActive());
//
//        long insert = db.insert(CUSTOMER_TABLE, null, cv);
//        // if insert value is > 0 then success else then fail
//
//        if(insert == -1)
//            return false;
//        else
//            return true;
//    }
//
//    public List<CustomerModel> getEveryOne(){
//        List<CustomerModel> returnlist = new ArrayList<>();
//        String  queryString = "SELECT * FROM " + CUSTOMER_TABLE;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(queryString,null);
//
//        if(cursor.moveToFirst()){
//            do{
//                int customerID = cursor.getInt(0);
//                String customerName = cursor.getString(1);
//                int customerAge =  cursor.getInt(2);
//                boolean customerIsActive = cursor.getInt(3) == 1 ? true:false;
//                CustomerModel cm = new CustomerModel(customerID,customerName,customerAge,customerIsActive);
//
//                returnlist.add(cm);
//            }while(cursor.moveToNext());
//        }else{
//            cursor.close();
//            db.close();
//            return returnlist;
//        }
//        return returnlist;
//    }
//
//    public boolean tangtang (CustomerModel cm){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String qs = "DELETE FROM " + CUSTOMER_TABLE + " WHERE ID = " + cm.getId();
//        Cursor c = db.rawQuery(qs,null);
//        if(c.moveToFirst())
//            return true;
//        else
//            return false;
//    }
//

}
