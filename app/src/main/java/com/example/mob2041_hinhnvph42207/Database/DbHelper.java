package com.example.mob2041_hinhnvph42207.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private  static final String DB_NAME = "PNLIB";
    static  final int DbVersion = 6;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DbVersion);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tao bang Thu Thu
        String  tb_ThuThu = ("CREATE TABLE ThuThu (" +
                "maTT TEXT PRIMARY KEY, " +
                "hoTen TEXT NOT NULL, " +
                "matKhau TEXT NOT NULL," +
                "Loaitaikhoan TEXT NOT NULL)");
        db.execSQL(tb_ThuThu);

        //Tao bang Thanh vien
        String  tb_ThanhVien = ("CREATE TABLE ThanhVien (" +
                "maTV INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "hoTen TEXT NOT NULL, " +
                "namSinh TEXT NOT NULL)");
        db.execSQL(tb_ThanhVien);

        //Tao bang Loai Sach
        String  tb_LoaiSach = ("CREATE TABLE LoaiSach (" +
                "maLoai INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenLoai  TEXT NOT NULL)");
        db.execSQL(tb_LoaiSach);

        //Tao bang Sach
        String  tb_Sach = "CREATE TABLE Sach(" +
                "maSach INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenSach TEXT NOT NULL, " +
                "giaThue INTEGER NOT NULL," +
                "maLoai INTEGER REFERENCES LoaiSach(maLoai))";
        db.execSQL(tb_Sach);

        //Tao bang Phieu Muon
        String  tb_PhieuMuon = "CREATE TABLE PhieuMuon (" +
                        "maPM INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "maTT TEXT REFERENCES ThuThu(maTT), " +
                        "maTV INTEGER REFERENCES ThanhVien(maTV), " +
                        "maSach INTEGER REFERENCES Sach(maSach)," +
                        "tienThue INTEGER NOT NULL," +
                        "traSach INTEGER NOT NULL," +
                        "ngay TEXT NOT NULL)";
        db.execSQL(tb_PhieuMuon);

        //insert date
        db.execSQL("INSERT INTO ThuThu VALUES ('thuthu1','Nguyen Admin','admin','Admin'), ('thuthu2','Nguyen Văn Thu ','123456','ThuThu')");
        db.execSQL("INSERT INTO ThanhVien VALUES (1,'Nguyen Admin','2000'), (2,'Admin','2000')");
        db.execSQL("INSERT INTO LoaiSach VALUES (1,'Ngôn ngữ lập trình'), (2,'Truyện Tranh'), (3,'Tiếng Anh')");
        db.execSQL("INSERT INTO Sach VALUES (1,'Java1',2000,1), (2,'Love',3000,2), (3,'Ngữ pháp',5000,3)");
        //trả sách: 1: đã trả - 0: chưa trả
        db.execSQL("INSERT INTO PhieuMuon VALUES" +
                "(1,'thuthu1',1,1,2000,0 ,'2022/11/7')," +
                "(2,'thuthu2',2,1,2000,1 ,'2022/11/7')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i != i1 ){
            db.execSQL("drop table if exists ThuThu");
            db.execSQL("drop table if exists ThanhVien");
            db.execSQL("drop table if exists LoaiSach");
            db.execSQL("drop table if exists Sach");
            db.execSQL("drop table if exists PhieuMuon");
            onCreate(db);

        }
    }
}
