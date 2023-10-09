package com.example.mob2041_hinhnvph42207.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.mob2041_hinhnvph42207.Database.DbHelper;


import java.util.ArrayList;
import java.util.List;

public class ThuThuDAO {
    DbHelper dbHelper;

    public ThuThuDAO(Context context) {
        dbHelper = new DbHelper(context);
    }
    // đăng phập
    public boolean checkLogin(String maTT,String matKhau) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM ThuThu WHERE maTT=? AND matKhau=?",new String[]{maTT, matKhau});
        if (cursor.getCount()  != 0){
            return true;
        }
            return false;
    }

    public boolean updatePass(String username, String oldPass, String newPass){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from ThuThu where maTT = ? and matKhau = ?", new String[]{username,oldPass});
        if (cursor.getCount() > 0){
            ContentValues values = new ContentValues();
            values.put("matKhau", newPass);
            long check = db.update("ThuThu",values,"maTT = ?",new String[]{username});
            if (check == -1){
                return false;
            }else {
                return true;
            }
        }
        return false;
    }


}
