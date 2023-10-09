package com.example.mob2041_hinhnvph42207.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mob2041_hinhnvph42207.Database.DbHelper;
import com.example.mob2041_hinhnvph42207.model.PhieuMuon;
import com.example.mob2041_hinhnvph42207.model.Sach;
import com.example.mob2041_hinhnvph42207.model.Top;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDAO {
    DbHelper dbHelper;

    public PhieuMuonDAO(Context context){
        dbHelper = new DbHelper(context);

    }

    public ArrayList<PhieuMuon> getDSPhieuMuon(){
        ArrayList<PhieuMuon> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT pm.maPM, tv.hoTen, sc.tenSach, pm.tienThue, pm.traSach, pm.ngay  From PhieuMuon pm, ThanhVien tv, Sach sc, ThuThu tt where pm.maTV = tv.maTV and pm.maSach = sc.maSach and pm.maTT = tt.maTT ORDER BY pm.maPM DESC",null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new PhieuMuon(cursor.getInt(0),cursor.getString(1),cursor.getString(2), cursor.getInt(3), cursor.getInt(4),cursor.getString(5)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean update(PhieuMuon pm){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maTT",pm.getMaTT());
        values.put("maTV", pm.getMaTV());
        values.put("maSach",pm.getMaSach());
        values.put("tienThue",pm.getTienThue());
        values.put("traSach",pm.getTrangThai());
        values.put("ngay",pm.getNgayThue());

        long check = db.update("PhieuMuon",values,"maPM = ?",new String[]{String.valueOf(pm.getMaPM())});
        if(check == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean delete(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long check = db.delete("PhieuMuon","maPM = ?",new String[]{String.valueOf(id)});
        if(check == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean updateTrangThai(int mapm){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("traSach",1);
        long check = db.update("PhieuMuon",values,"maPM = ?",new String[]{String.valueOf(mapm)});
        if(check == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean insert(PhieuMuon pm){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maTT",pm.getMaTT());
        values.put("maTV", pm.getMaTV());
        values.put("maSach",pm.getMaSach());
        values.put("tienThue",pm.getTienThue());
        values.put("traSach",pm.getTrangThai());
        values.put("ngay",pm.getNgayThue());

        long check = db.insert("PhieuMuon",null,values);
        if(check == -1){
            return false;
        }else{
            return true;
        }
    }



}
