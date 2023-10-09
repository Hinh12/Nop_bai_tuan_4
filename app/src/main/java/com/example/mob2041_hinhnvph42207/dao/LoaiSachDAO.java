package com.example.mob2041_hinhnvph42207.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mob2041_hinhnvph42207.Database.DbHelper;
import com.example.mob2041_hinhnvph42207.model.LoaiSach;


import java.util.ArrayList;


public class LoaiSachDAO {
    DbHelper dbHelper;

    public LoaiSachDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<LoaiSach> getDSLoaiSach(){
        ArrayList<LoaiSach> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from LoaiSach",null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new LoaiSach(cursor.getInt(0), cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean insert(String tenLoai){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenLoai", tenLoai);
        long check = db.insert("LoaiSach",null,values);
        if(check == -1){
            return false;
        }else{
            return true;
        }
    }

    public int deleteLS(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Sach where maLoai = ?", new String[]{String.valueOf(id)});
        if(cursor.getCount() != 0){
            return -1;
        }
        long check = db.delete("LoaiSach","maLoai = ?", new String[]{String.valueOf(id)});
        if(check == -1){
            return 0;
        }else{
            return 1;
        }
    }

    public boolean update(LoaiSach loaiSach){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenLoai", loaiSach.getTenLoai());
        long check = db.update("LoaiSach",values,"maLoai = ?", new String[]{String.valueOf(loaiSach.getMaLoai())});
        if(check == -1){
            return false;
        }else{
            return true;
        }
    }


}
