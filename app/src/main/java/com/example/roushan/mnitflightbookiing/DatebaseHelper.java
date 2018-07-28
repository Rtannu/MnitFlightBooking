package com.example.roushan.mnitflightbookiing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ROUSHAN on 10-04-2017.
 */

public class DatebaseHelper extends SQLiteOpenHelper {
    public static final String DATA_BASE="projectAirline.db";
    public static final String TABLE_NAME1="search";
    public static final String COL_2="SOURCE";
    public static final String COL_3="DESTINATION";
    public static final String COL_4="DEPARTURE";
    public static final String COL_5="CLASS";
    public static final String COL_6="COMPANY";
    public static final String COL_7="PLACE";
    public static final String COL_8="SEAT";
    public static final String COL_9="PRICE";



    //information about signup
    public static final String TABLE_NAME2="signup";

    //information about airport
    public static final String TABLE_NAME3="airport";

    public DatebaseHelper(Context context) {
        super(context, DATA_BASE, null, 1);
      //  SQLiteDatabase db=this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME1 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,SOURCE TEXT,DESTINATION TEXT,DEPARTURE TEXT,CLASS TEXT,COMPANY TEXT,PLACE TEXT,SEAT TEXT,PRICE TEXT)");

        db.execSQL("CREATE TABLE " + TABLE_NAME3 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,AIRPORT TEXT,CITY TEXT)");


        db.execSQL("CREATE TABLE " + TABLE_NAME2 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,EMAIL TEXT,PASSWORD TEXT,RATE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP  TABLE IF EXISTS" + TABLE_NAME1);
        db.execSQL("DROP  TABLE IF EXISTS" + TABLE_NAME2);
        db.execSQL("DROP  TABLE IF EXISTS" + TABLE_NAME3);

        onCreate(db);


    }
    //function for search flight
    public Cursor getData(String from,String des,String clas  )
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM " + TABLE_NAME1 + " WHERE SOURCE = '" + from + "' AND DESTINATION = '" + des + "' AND CLASS= '" + clas + "' ";
        Cursor cursor;
        cursor = db.rawQuery(query,null);
        return cursor;
    }
    public   boolean signUp(String name,String email,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("NAME",name);
        contentValues.put("EMAIL",email);
        contentValues.put("PASSWORD",password);
        long result=db.insert(TABLE_NAME2,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;

    }

    public   boolean insertAirport(String airport,String city)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("AIRPORT",airport);
        contentValues.put("CITY",city);
        long result=db.insert(TABLE_NAME3,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;

    }

    public Cursor searchAirpot(String city)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM " + TABLE_NAME3 + " WHERE CITY = '" + city + "'";
        Cursor cursor;
        cursor = db.rawQuery(query,null);
        return cursor;
    }


    public   boolean insertflight(String from,String des,String dep,String clas,String comp,String place ,String seat ,String price)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("SOURCE",from);
        contentValues.put("DESTINATION",des);
        contentValues.put("DEPARTURE",dep);
        contentValues.put("CLASS",clas);
        contentValues.put("COMPANY",comp);
        contentValues.put("PLACE",place);
        contentValues.put("SEAT",seat);
        contentValues.put("PRICE",price);

        long result=db.insert(TABLE_NAME1,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;

    }


    public int  login(String email,String password)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM " + TABLE_NAME2 + " WHERE EMAIL = '" + email + "' AND PASSWORD = '" + password + "' ";
        Cursor cursor;
        cursor = db.rawQuery(query,null);
        int no=cursor.getCount();
        return no;
    }



    public Cursor book(int s_no)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM " + TABLE_NAME1 + " WHERE ID = '" + s_no + "'";
        Cursor cursor;
        cursor = db.rawQuery(query,null);
        return cursor;
    }

    public Cursor getCustomerName(String email)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM " + TABLE_NAME2 + " WHERE EMAIL = '" + email + "'";
        Cursor cursor;
        cursor = db.rawQuery(query,null);
        return cursor;
    }
    public int AccountDelete(String email)
    {
        SQLiteDatabase db=this.getReadableDatabase();
       return db.delete(TABLE_NAME2,"EMAIL = '"+email+"'",null);
    }

    public boolean UpdateProfile(int id,String name,String email,String password)
         {
             SQLiteDatabase db=this.getWritableDatabase();
             ContentValues contentValues=new ContentValues();
             contentValues.put("ID",id);
             contentValues.put("NAME",name);
             contentValues.put("EMAIL",email);
             contentValues.put("PASSWORD",password);
            int  result= db.update(TABLE_NAME2,contentValues,"ID= '" + id + "'",null);
             if(result>0)
                 return  true;
             else
                 return  false;
         }

    public Cursor Status(int s_no)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM " + TABLE_NAME1 + " WHERE ID = '" + s_no + "'";
        Cursor cursor;
        cursor = db.rawQuery(query,null);
        Log.d("FrindsInDb","FriendExists, count="+cursor.getCount());
        return cursor;
    }

    public boolean  CheckSignup(String email)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM " + TABLE_NAME2 + " WHERE EMAIL = '" + email + "'";
        Cursor cursor;
        cursor = db.rawQuery(query,null);
        int no=cursor.getCount();
        if(no>0)
            return true;
        else
            return false;
    }

    public Cursor Avaiable(int s_no)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM " + TABLE_NAME1 + " WHERE ID = '" + s_no + "'";
        Cursor cursor;
        cursor = db.rawQuery(query,null);
      //  int value= Integer.parseInt(cursor.getString(7));
     //   Log.d("FrindsInDb","FriendExists, count="+cursor.getCount());
        return  cursor;
    }
}
