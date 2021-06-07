package DAO;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import Adapter.GiaoDich;
import Adapter.NganSach;

import java.sql.Date;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    //tên của cơ sở dữ liệu
    private static final String DATABASE_NAME ="Conspen";
    //thông tin bảng người dùng
    private static final String TABLE_NGUOIDUNG = "NguoiDung";
    private static final String COLUMN_NGUOIDUNG_ID = "NguoiDung_id";
    private static final String COLUMN_NGUOIDUNG_USERNAME = "NguoiDung_username";
    private static final String COLUMN_NGUOIDUNG_PASSWORD = "NguoiDung_password";
    //thông tin bảng giao dịch
    private static final String TABLE_GIAODICH = "GiaoDich";
    private static final String COLUMN_GIAODICH_ID = "GiaoDich_idgiaodich";
    private static final String COLUMN_GIAODICH_IDNGUOIDUNG = "GiaoDich_idnguoidung";
    private static final String COLUMN_GIAODICH_TENGD = "GiaoDich_tengd";
    private static final String COLUMN_GIAODICH_NGAYTAO = "GiaoDich_ngaytao";
    private static final String COLUMN_GIAODICH_SOTIEN = "GiaoDich_sotien";
    //thông tin bảng ngân sách
    private static final String TABLE_NGANSACH = "NganSach";
    private static final String COLUMN_NGANSACH_ID = "NganSach_idngansach";
    private static final String COLUMN_NGANSACH_IDNGUOIDUNG = "NganSach_idnguoidung";
    private static final String COLUMN_NGANSACH_TONGTIEN = "NganSach_tongtien";
    private static final String COLUMN_NGANSACH_STARTDATE = "NganSach_startDate";
    private static final String COLUMN_NGANSACH_ENDDATE = "NganSach_endDate";

    public MyDatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //tạo bảng người dùng
        String querynguoidung = "CREATE TABLE "+ TABLE_NGUOIDUNG +"("
                + COLUMN_NGUOIDUNG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NGUOIDUNG_USERNAME + " STRING,"
                + COLUMN_NGUOIDUNG_PASSWORD + " STRING" + ")";
        db.execSQL(querynguoidung);
        //tạo bảng giao dịch
        String querygiaodich = "CREATE TABLE "+ TABLE_GIAODICH + "("
                + COLUMN_GIAODICH_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_GIAODICH_IDNGUOIDUNG + " INTEGER,"
                + COLUMN_GIAODICH_TENGD + " STRING,"
                + COLUMN_GIAODICH_NGAYTAO + " STRING,"
                + COLUMN_GIAODICH_SOTIEN + " FLOAT"+ ")";
        db.execSQL(querygiaodich);
        //tạo bảng ngân sách
        String queryngansach = "CREATE TABLE "+ TABLE_NGANSACH + "("
                + COLUMN_NGANSACH_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NGANSACH_IDNGUOIDUNG + " INTEGER,"
                + COLUMN_NGANSACH_TONGTIEN + " FLOAT,"
                + COLUMN_NGANSACH_STARTDATE + " DATE,"
                + COLUMN_NGANSACH_ENDDATE + " DATE"+ ")";
        db.execSQL(queryngansach);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NGUOIDUNG);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_GIAODICH);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NGANSACH);
        onCreate(db);
    }
    //Các hàm xử lý Người dùng

    //Các hàm xử lý Giao dịch
    public List<GiaoDich> getAllGiaoDich(){
        List<GiaoDich> lgd = new ArrayList<GiaoDich>();
        String query = "SELECT * FROM "+TABLE_GIAODICH;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                GiaoDich gd = new GiaoDich();
                gd.setIdgiaodich(cursor.getInt(0));
                gd.setIdnguoidung(cursor.getInt(1));
                gd.setTengd(cursor.getString(3));
                gd.setNgaytao(Date.valueOf(cursor.getString(4)));
                gd.setSotien(cursor.getFloat(5));
            } while(cursor.moveToNext());
        }
        return lgd;
    }
    public void createGiaoDich(GiaoDich gd){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues ct = new ContentValues();
        ct.put(COLUMN_GIAODICH_IDNGUOIDUNG,gd.getIdnguoidung());
        ct.put(COLUMN_GIAODICH_TENGD,gd.getTengd());
        ct.put(COLUMN_GIAODICH_NGAYTAO,gd.getNgaytao().toString());
        ct.put(COLUMN_GIAODICH_SOTIEN,gd.getSotien());
        db.insert(TABLE_GIAODICH,null,ct);
        db.close();
    }
    public int updateGiaoDich(GiaoDich gd){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues ct = new ContentValues();
        ct.put(COLUMN_GIAODICH_TENGD,gd.getTengd());
        ct.put(COLUMN_GIAODICH_NGAYTAO,gd.getNgaytao().toString());
        ct.put(COLUMN_GIAODICH_SOTIEN,gd.getSotien());
        return db.update(TABLE_GIAODICH,ct,COLUMN_GIAODICH_ID + " =?",new String[]{String.valueOf(gd.getIdgiaodich())});
    }
    public void deleteGiaoDich(GiaoDich gd){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GIAODICH,COLUMN_GIAODICH_ID+" =?",new String[]{String.valueOf(gd.getIdgiaodich())});
        db.close();
    }

    //Các hàm xử lý Ngân sách
    public List<NganSach> getAllNganSach(){
        List<NganSach> lns = new ArrayList<NganSach>();
        String query = "SELECT * FROM "+TABLE_NGANSACH;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                NganSach ns = new NganSach();
                ns.setIdngansach(cursor.getInt(0));
                ns.setIdkhachhang(cursor.getInt(1));
                ns.setTongtien(cursor.getFloat(3));
                ns.setStartDate(Date.valueOf(cursor.getString(4)));
                ns.setEndDate(Date.valueOf(cursor.getString(5)));

            } while(cursor.moveToNext());
        }
        return lns;
    }
    public void createNS(NganSach ns){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues ct = new ContentValues();
        ct.put(COLUMN_NGANSACH_IDNGUOIDUNG,ns.getIdkhachhang());
        ct.put(COLUMN_NGANSACH_TONGTIEN,ns.getTongtien());
        ct.put(COLUMN_NGANSACH_STARTDATE,ns.getStartDate().toString());
        ct.put(COLUMN_NGANSACH_ENDDATE, ns.getEndDate().toString());
        db.insert(TABLE_NGANSACH,null,ct);
        db.close();
    }
    public int updateNS(NganSach ns){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues ct = new ContentValues();
        ct.put(COLUMN_NGANSACH_TONGTIEN,ns.getTongtien());
        ct.put(COLUMN_NGANSACH_STARTDATE,ns.getStartDate().toString());
        ct.put(COLUMN_NGANSACH_ENDDATE,ns.getEndDate().toString());
        return db.update(TABLE_NGANSACH,ct,COLUMN_NGANSACH_ID + " =?",new String[]{String.valueOf(ns.getIdngansach())});
    }
    public void deleteNS(NganSach ns){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NGANSACH,COLUMN_NGANSACH_ID+" =?",new String[]{String.valueOf(ns.getIdngansach())});
        db.close();
    }
}
