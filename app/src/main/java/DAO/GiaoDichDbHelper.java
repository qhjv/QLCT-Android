package DAO;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import Adapter.GiaoDich;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

//Lớp xử lý cơ sở dữ liệu với SQLite
public class GiaoDichDbHelper extends SQLiteOpenHelper {
    private static final String TAG = "GiaodichDbHelper";
    private static final String DATABASE_NAME = "mydb.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_GIAODICH = "giaodich";

    public GiaoDichDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //tạo bảng
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "Create table");
        String queryCreateTable = "CREATE TABLE " + TABLE_GIAODICH + " ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR (255) NOT NULL, " +
                "price DECIMAL DEFAULT (0)" + ")";
        db.execSQL(queryCreateTable);
    }
    //upgrade version cho bảng
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GIAODICH);
        onCreate(db);
    }
    //lấy tất cả dữ liệu từ bảng giao dịch
    public List<GiaoDich> getAllGiaoDich() {
        List<GiaoDich> giaodichs = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, name, price from giaodich", null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int giaodichID = cursor.getInt(0);
            String giaodichName = cursor.getString(1);
            int giaodichPrice = cursor.getInt(3);

            giaodichs.add(new GiaoDich(giaodichID, giaodichName, giaodichPrice));
            cursor.moveToNext();
        }
        cursor.close();
        return giaodichs;
    }
    //lấy giao dịch theo id
    public GiaoDich getGiaoDichbyID(int ID) {
        GiaoDich giaodich = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, name, price from giaodich where id =?", new String[]{ID + ""});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int giaodichID = cursor.getInt(0);
            String giaodichName = cursor.getString(1);
            int giaodichPrice = cursor.getInt(3);
            giaodich = new GiaoDich(giaodichID, giaodichName, giaodichPrice);
        }
        cursor.close();
        return giaodich;
    }
    public void updateGiaoDich(GiaoDich giaodich){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE giaodich SET name=?, price = ?  where id = ?",
                new String[]{giaodich.name,giaodich.price+"",giaodich.giaodichID+""});
    }
    //thêm giao dịch vào bảng
    public void insertGiaoDich(GiaoDich giaodich){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO giaodich (name, price) VALUES(?,?)",
                new String[]{giaodich.name,giaodich.price+""});
    }
    public void deleteGiaodichbyID(int giaodichID){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM product where id = ?", new String[]{String.valueOf(giaodichID)});
    }
}