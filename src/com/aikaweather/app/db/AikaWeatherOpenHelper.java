package com.aikaweather.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class AikaWeatherOpenHelper extends SQLiteOpenHelper {
	// �̳�SQLiteOpenHelper���.getReadableDatabase()

	/**
	 * Province������䣨ʡ��
	 * INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ��������������Ϊ��
	 */
	public static final String CREATE_PROVINCE = "create table Province(" 
	               + "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
			       + "province_name text," 
	               + "province_code text)";
	/**
	 * City������䣨�У�
	 */
	public static final String CREATE_CITY = "create table City("
	               + "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
			       + "city_name text," 
	               + "city_code text," 
			       + "province_id integer)";
	/**
	 * County������䣨�أ�
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
		db.execSQL(CREATE_PROVINCE);//����Province��ʡ����
		//Toast.makeText(mContext, "����ɹ�1", Toast.LENGTH_SHORT).show();
		db.execSQL(CREATE_CITY);//����City���У���
		//Toast.makeText(mContext, "����ɹ�2", Toast.LENGTH_SHORT).show();
		db.execSQL(CREATE_COUNTY);//����County(��)��
	//	Toast.makeText(mContext, "����ɹ�3", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		//�������ݿ�
//		db.execSQL("drop table if exists Province");
//		db.execSQL("drop table if exists City");
//		db.execSQL("drop table if exists County");
//		onCreate(db);
	}

}
