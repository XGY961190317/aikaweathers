package com.aikaweather.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class AikaWeatherOpenHelper extends SQLiteOpenHelper {
	// 继承SQLiteOpenHelper获得.getReadableDatabase()

	/**
	 * Province表建表语句（省）
	 * INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT 自增，主键，不为空
	 */
	public static final String CREATE_PROVINCE = "create table Province(" 
	               + "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
			       + "province_name text," 
	               + "province_code text)";
	/**
	 * City表建表语句（市）
	 */
	public static final String CREATE_CITY = "create table City("
	               + "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
			       + "city_name text," 
	               + "city_code text," 
			       + "province_id integer)";
	/**
	 * County表建表语句（县）
	 */
	public static final String CREATE_COUNTY = "create table County(" 
	               + "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
			       + "county_name text," 
	               + "county_code text," 
			       + "city_id integer)";
    private Context mContext;
	public AikaWeatherOpenHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		mContext=context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_PROVINCE);//创建Province（省）表
		//Toast.makeText(mContext, "建表成功1", Toast.LENGTH_SHORT).show();
		db.execSQL(CREATE_CITY);//创建City（市）表
		//Toast.makeText(mContext, "建表成功2", Toast.LENGTH_SHORT).show();
		db.execSQL(CREATE_COUNTY);//创建County(县)表
	//	Toast.makeText(mContext, "建表成功3", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		//升级数据库
//		db.execSQL("drop table if exists Province");
//		db.execSQL("drop table if exists City");
//		db.execSQL("drop table if exists County");
//		onCreate(db);
	}

}
