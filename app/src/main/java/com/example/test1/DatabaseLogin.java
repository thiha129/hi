package com.example.test1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseLogin extends SQLiteOpenHelper {
    public DatabaseLogin(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        
    }
    //select
    public Cursor GetData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }
    //insert
    public void UpData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public long addUser(String user, String password, String hoten, String Sodienthoai,String Ngay) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Ten", user);
        contentValues.put("Pass", password);
        contentValues.put("Hovaten", hoten);
        contentValues.put("SoDienThoai", Sodienthoai);
        contentValues.put("Ngay",Ngay);
        long res = db.insert("TaiKhoan2", null, contentValues);
        db.close();
        return res;
    }
    public boolean checkUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor1 = db.rawQuery("Select * from TaiKhoan where Ten = ? ", new String[]{username});
        int count = cursor1.getCount();
        cursor1.close();
        db.close();
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }
    public boolean check(String name, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from TaiKhoan where Ten=? and Pass=?", new String[]{name, pass});
        if (cursor.getCount() > 0) return true;
        else return false;
    }
    public Cursor ThongTinTaiKhoan(String name, String pass){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from TaiKhoan where Ten=? and Pass=?", new String[]{name, pass});
        return cursor;
    }
    public boolean checkpass(String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from TaiKhoan where Pass=?", new String[]{pass});
        if (cursor.getCount() > 0) return true;
        else return false;
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
