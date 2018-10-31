package com.example.user.sqlite.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.sqlite.model.Kota;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME ="DB_kota";

    private static final String TABLE_NAME ="tabel_kota";
    private static final String COL1 ="ID";
    private static final String COL2 ="kota";

    public Database(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createtable= "CREATE TABLE " + TABLE_NAME + "("+COL1+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL2 + " TEXT)";
        db.execSQL(createtable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // FUNGSI UNTUK TAMBAH DATA Kota
    public void tambahKota(Kota kota){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL2, kota.getKota());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // FUNGSI UNTUK AMBIL 1 DATA Kota
    public Kota getKota(int id_kota){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{COL1, COL2},
                COL1 + "=?", new String[]{String.valueOf(id_kota)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Kota kota = new Kota(cursor.getString(1));
        return kota;
    }

    // FUNGSI UNTUK AMBIL SEMUA DATA Kota
    public List<Kota> getSemuaKota(){
        List<Kota> kotaList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                Kota kota = new Kota(cursor.getString(1));
                kotaList.add(kota);
            } while (cursor.moveToNext());
        }
        return kotaList;
    }

    // FUNGSI MENGHITUNG ADA BEBERAPA DATA
    public int getKotaaCount(){
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }
}
