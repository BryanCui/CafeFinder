package com.findcafe;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Tianying on 5/04/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private final static String databaseName = "FindCafe.db";
    private final static String tableName = "cafes_table";
    private final static int databaseVersion = 1;
    private SQLiteDatabase database;
    private Context context;

    public DatabaseHelper(Context context)
    {
        super(context, databaseName, null, databaseVersion);
        this.context = context;
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        executeAssetsSQL(db, "createDatabase.sql");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + tableName;
        db.execSQL(sql);
        onCreate(db);
    }

    public void clearDatabase()
    {
        database.execSQL("DROP TABLE IF EXISTS " + tableName);

        onCreate(database);
    }

    public Cursor selectDataFromDatabase()
    {
        Cursor cursor = null;
        String sql = "SELECT cafe_id, title, address, latitude, longitude, distance FROM " + tableName + " ORDER BY distance";
        cursor = database.rawQuery(sql, null);
        if(cursor.moveToNext()) {
            System.out.println(cursor.getString(2) + "============`=`=`=`=`=`=`");
        }
        return cursor;
    }

    public void clearTable() {
        String deleteSql = "DELETE FROM cafes_table";
        database.execSQL(deleteSql);
    }

    public void insertDataIntoDatabase(Cafe cafe)
    {
        String address = cafe.getCafeAddress().replaceAll("\"", "");
        String insertSql = "insert into cafes_table(cafe_id, title, address, latitude, longitude, distance) " +
                "values('" + cafe.getCafeId() + "','" + cafe.getCafeName().replaceAll("'", "") + "','" +address + "','" + cafe.getCafeLatitude() + "', '" + cafe.getCafeLongitude() + "', '" + cafe.getCafeDistance() + "')";
//        System.out.println("Id: " + cafe.getCafeId());
//        System.out.println("Name: " + cafe.getCafeName().replaceAll("'", ""));
//        System.out.println("Address: " + cafe.getCafeAddress());
//        System.out.println("Lat: " + cafe.getCafeLatitude());
//        System.out.println("Lng: " + cafe.getCafeLongitude());
//        System.out.println("Distance: " + cafe.getCafeDistance());
        database.execSQL(insertSql);
    }

    public void updateDataInDatabase(String updateSql)
    {
        database.rawQuery(updateSql, null);
    }

    public void closeDatabase()
    {
        database.close();
    }

    private void executeAssetsSQL(SQLiteDatabase db, String sqlFileName) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(context.getAssets()
                    .open(sqlFileName)));

            String line;
            String buffer = "";
            while ((line = in.readLine()) != null) {
                buffer += line;
                if (line.trim().endsWith(";")) {
                    db.execSQL(buffer.replace(";", ""));
                    buffer = "";
                }
            }

        } catch (IOException e) {
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
            }
        }
    }

    public boolean isTableExists(String tableName) {
        Cursor cursor = database.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"+tableName+"'", null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }

}
