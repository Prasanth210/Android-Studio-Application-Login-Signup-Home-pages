package com.example.carservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Signup.db";

    public DBHelper(Context context) {
        super(context,"Signup.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MYDB) {
        MYDB.execSQL("Create Table users(username TEXT primary key, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MYDB, int oldVersion, int newVersion) {
        MYDB.execSQL("drop table if exists users");

    }
    public boolean insertData(String username,String password){
        SQLiteDatabase MYDB = this.getReadableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result = MYDB.insert("users",null,contentValues);
        if(result==-1) return false;
        else
            return true;
    }
    public boolean checusername(String username){
        SQLiteDatabase MYDB = this.getWritableDatabase();
        Cursor curser= MYDB.rawQuery("Select * from users where username= ?",new String[] {username});
        return curser.getCount() > 0;
    }
    public boolean checusernamepassword(String username,String password) {
        SQLiteDatabase MYDB = this.getWritableDatabase();
        Cursor curser = MYDB.rawQuery("Select * from users where username = ? and password= ?", new String[]{username,password});
        return curser.getCount() > 0;
    }
}
