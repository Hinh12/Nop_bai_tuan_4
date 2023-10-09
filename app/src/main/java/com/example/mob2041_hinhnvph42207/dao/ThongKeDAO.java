package com.example.mob2041_hinhnvph42207.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mob2041_hinhnvph42207.Database.DbHelper;
import com.example.mob2041_hinhnvph42207.model.Sach;

import java.util.ArrayList;

public class ThongKeDAO {
    DbHelper dbHelper;
    public ThongKeDAO(Context context){
        dbHelper = new DbHelper(context);
    }

    public ArrayList<Sach> getTop10(){
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT pm.maSach, sc.tenSach, COUNT(pm.maSach) AS soLanMuon FROM PhieuMuon pm, Sach sc WHERE pm.maSach = sc.maSach GROUP by pm.maSach, sc.tenSach ORDER by COUNT(pm.maSach) DESC LIMIT 10",null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new Sach(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public int getDoanhThu(String Start, String End){
        Start = Start.replace("/","");
        End = End.replace("/","");
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(tienThue) FROM PhieuMuon WHERE substr(ngay,7) || substr(ngay,4,2) || substr(ngay,1,2) BETWEEN ? and ?",new String[]{Start,End});
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        return 0;
    }

}
