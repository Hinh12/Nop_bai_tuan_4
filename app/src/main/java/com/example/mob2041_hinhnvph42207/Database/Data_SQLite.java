package com.example.mob2041_hinhnvph42207.Database;

public class Data_SQLite {
    public static final String INSERT_THU_THU = "INSERT INTO ThuThu(maTT, hoTen,matKhau) VALUES" +
            "('admin','Nguyen Admin','admin')," +
            "('hinh','Nguyen ','123456')";
    public static final  String INSERT_THANH_VIEN = "INSERT INTO ThanhVien(hoTen, namsinh) VALUES" +
            "('Nguyen Admin','2000')," +
            "('Teo','2011')," +
            "('Nguyen','2000')," +
            "('Admin','2000')," +
            "('Nguyen Hin','2002');";

    public static final  String INSERT_LOAI_SACH = "INSERT INTO LoaiSach(tenloai) VALUES" +
            "('Ngôn ngữ lập trình')," +
            "('Ngôn ngữ lập trình')," +
            "('Ngôn ngữ lập trình')," +
            "('Ngôn ngữ lập trình');";
    public static final  String INSERT_SACH = "INSERT INTO Sach(tenSach,giaThue,maloai) VALUES" +
            "('Java1','2000','1')," +
            "('Java2','3000','1')," +
            "('Java3','4000','1')," +
            "('Java4','5000','1');";
    public static final  String INSERT_PHIEU_MUON = "INSERT INTO PhieuMuon(maTT,maTV,maSach,tienThue,ngay,traSach) VALUES" +
            "('admin','1','1','2000','2022/11/7',0)," +
            "('hih','2','1','2000','2022/11/7',0);";
}
