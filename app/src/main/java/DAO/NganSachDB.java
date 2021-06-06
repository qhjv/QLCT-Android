package DAO;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import Adapter.NganSach;
import Adapter.ListNganSach;

public class NganSachDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="test.db";
    private static  final int DATABASE_VERSION=1;
    public NganSachDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase db) {
        String sql="create table ngansach(id integer primary key autoincrement, mucchi number, thoigian text)";
        db.execSQL(sql);
    }

    @Override
    public void onOpen(android.database.sqlite.SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int themTK(NganSach a){
        ContentValues contentValues=new ContentValues();
        contentValues.put("mucchi", a.getMuchi());
        contentValues.put("thoigian", a.getThoigian());
        android.database.sqlite.SQLiteDatabase st=getWritableDatabase();
        return (int) st.insert("ngansach", null, contentValues);
    }

    public ArrayList<NganSach> getTK(){
        ArrayList<NganSach> as = new ArrayList<>();
        android.database.sqlite.SQLiteDatabase st=getReadableDatabase();
        Cursor re=st.query("ngansach", null,null,null,null,null,null);
        while ((re.moveToNext())){
            as.add(new NganSach(re.getInt(0), re.getLong(1),re.getString(2)));
        }
        return as;
    }
}
