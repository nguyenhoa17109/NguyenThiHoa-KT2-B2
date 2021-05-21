package com.nguyenhoa.nguyenthihoa_kt2_b2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SQLiteVeMayBay extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "VeMayBay.db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteVeMayBay(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE ve (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "location TEXT," +
                "datestart TEXT," +
                "hanhli BOOLEAN)";
        db.execSQL(sql);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion,
                          int newVersion) {

    }

    public long addVMB(VeMayBay s){
        ContentValues v = new ContentValues();
        v.put("name", s.getName());
        v.put("location", s.getLocation());
        v.put("datestart", s.getDateStart());
        v.put("hanhli", s.isHanhli());
        SQLiteDatabase st = getWritableDatabase();
        return st.insert("ve", null, v);
    }

    //get ve by id
    public VeMayBay getVMB(int id) {
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("ve", null,
                whereClause, whereArgs, null, null, null);
        if (cursor.moveToNext()) {
            int idkh = cursor.getInt(0);
            String name = cursor.getString(1);
            String location = cursor.getString(2);
            String datestart = cursor.getString(3);
            boolean hanhli = cursor.getInt(4)==1;
            cursor.close();
            return new VeMayBay(idkh, name, location, datestart, hanhli);
        }
        return null;
    }

    //update
    public int updateVMB(VeMayBay s) {
        ContentValues v = new ContentValues();
        v.put("name", s.getName());
        v.put("location", s.getLocation());
        v.put("datestart", s.getDateStart());
        v.put("hanhli", s.isHanhli());
        String whereClause = "id = ?";
//        String.valueOf(student.getId())
        String[] whereArgs = {Integer.toString(s.getId())};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.update("ve",
                v, whereClause, whereArgs);
    }

    //delete
    public int deleteVMB(int id) {
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("ve",
                whereClause, whereArgs);
    }


    public List<VeMayBay> getAll(){
        List<VeMayBay> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        Cursor cursor = st.query("ve", null, null
                , null, null, null, null);
        while(cursor.moveToNext()){
            int idkh = cursor.getInt(0);
            String name = cursor.getString(1);
            String location = cursor.getString(2);
            String datestart = cursor.getString(3);
            boolean hanhli = cursor.getInt(4)==1;
            list.add(new VeMayBay(idkh, name, location, datestart, hanhli));
        }
        return list;
    }
    public List<VeMayBay> getStudentByName(String name) {
        List<VeMayBay> list=new ArrayList<>();
        String whereClause = "name LIKE ?";
        String[] whereArgs = {"%" + name + "%"};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("ve", null, whereClause, whereArgs, null, null, null, null);
        while (cursor != null && cursor.moveToNext()) {
            int idkh = cursor.getInt(0);
            String namea = cursor.getString(1);
            String location = cursor.getString(2);
            String datestart = cursor.getString(3);
            boolean hanhli = cursor.getInt(4)==1;
            list.add(new VeMayBay(idkh, namea, location, datestart, hanhli));
        }
        cursor.close();
        return list;
    }
}
