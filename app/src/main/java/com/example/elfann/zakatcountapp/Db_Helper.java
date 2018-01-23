package com.example.elfann.zakatcountapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.elfann.zakatcountapp.model.Mdl_Zf;

import java.util.ArrayList;

/**
 * Created by Elfan N on 21/01/2018.
 */

public class Db_Helper extends SQLiteOpenHelper{

    private final static String DATABASE_NAME = "zakatdata";
    private final static int DATABASE_VERSION = 1;
    private final static String TABEL_ZF = "tbzf";

    private final static String FITRAH_ID = "id";
    private final static String COL_NAMEKEPKEL = "namaKK";
    private final static String COL_JUMLAHANGKEL = "jumlahAngKel";
    private final static String COL_TOTALZAKATFITRAH = "totalZakatFitrah";

    public Db_Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABEL_ZF+"("
            +FITRAH_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COL_JUMLAHANGKEL+" INTEGER, "
            +COL_TOTALZAKATFITRAH+ " INTEGER, "
            +COL_NAMEKEPKEL+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABEL_ZF);
        onCreate(db);
    }

    public void saveDataZF(Mdl_Zf mdl_zf){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_JUMLAHANGKEL, mdl_zf.getJumlahKel());
        contentValues.put(COL_TOTALZAKATFITRAH, mdl_zf.getTotalZakat());
        contentValues.put(COL_NAMEKEPKEL, mdl_zf.getNamaKK());
        sqLiteDatabase.insert(TABEL_ZF, null, contentValues);
        sqLiteDatabase.close();
    }

    public ArrayList<Mdl_Zf> getAllDataFitr(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String [] allColums = {
                FITRAH_ID,
                COL_JUMLAHANGKEL,
                COL_TOTALZAKATFITRAH,
                COL_NAMEKEPKEL
        };

        Cursor cursor = sqLiteDatabase.query(TABEL_ZF, allColums,
                null, null, null, null, null);

        ArrayList<Mdl_Zf> tempKembalian = new ArrayList<>();

        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                int id = cursor.getInt(0);
                int jumlahKel = cursor.getInt(1);
                int totalZakat = cursor.getInt(2);
                String namaKK = cursor.getString(3);

                Mdl_Zf mdl_zf =new Mdl_Zf();
                mdl_zf.setId(id);
                mdl_zf.setJumlahKel(jumlahKel);
                mdl_zf.setTotalZakat(totalZakat);
                mdl_zf.setNamaKK(namaKK);
                tempKembalian.add(mdl_zf);
                cursor.moveToNext();
            }
        }
        sqLiteDatabase.close();
        return tempKembalian;
    }

    public void deleteDataZfitr(Mdl_Zf mdl_zf){
        int id = mdl_zf.getId();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //delete cara 1
        sqLiteDatabase.delete(TABEL_ZF, FITRAH_ID+"="+id, null);
        //delete cara ke 2
        /*sqLiteDatabase.delete((TABEL_ZF, FITRAH_ID)+"=?",
        new String[]{String.valueOf(id)});*/
        sqLiteDatabase.close();
    }
}
