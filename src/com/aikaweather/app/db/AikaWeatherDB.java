package com.aikaweather.app.db;

import java.util.ArrayList;
import java.util.List;

import com.aikaweather.app.model.City;
import com.aikaweather.app.model.County;
import com.aikaweather.app.model.Province;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AikaWeatherDB {
	/**
	 * ���ݿ���
	 */
	public static final String DB_NAME = "aika_weather";
	/**
	 * ����
	 */
	public String TABLE_PROVINCE = "Province";
	public String TABLE_CITY = "City";
	public String TABLE_COUNTY = "County";
	/**
	 * ���ݿ�汾
	 */
	private static final int VERAION = 1;
	private static AikaWeatherDB aikaWeatherDB;
	private SQLiteDatabase db;

	/**
	 * ��д���췽��
	 */
	public AikaWeatherDB(Context context) {
		AikaWeatherOpenHelper dbHelper = new AikaWeatherOpenHelper(context, DB_NAME, null, VERAION);
		db = dbHelper.getReadableDatabase();
	}

	/**
	 * ���AikaWeatherDB���ʾ��
	 */
	public synchronized static AikaWeatherDB getInstance(Context context) {
		if (aikaWeatherDB == null) {
			aikaWeatherDB = new AikaWeatherDB(context);
		}
		return aikaWeatherDB;
	}

	/**
	 * ��Provinceʾ���洢�����ݿ�
	 */
	public void saveProvince(Province province) {
		if (province != null) {
			ContentValues values = new ContentValues();
			values.put("province_name", province.getProvinceName());
			values.put("province_code", province.getProvinceCode());
			db.insert(TABLE_PROVINCE, null, values);
		}
	}

	/**
	 * �����ݿ��ȡȫ�����е�ʡ����Ϣ
	 * 
	 * @return
	 */
	public List<Province> loadProvince() {
		Cursor cursor = db.query(TABLE_PROVINCE, null, null, null, null, null, null);
		List<Province> list = new ArrayList<Province>();
		while (cursor.moveToFirst()) {
			Province province = new Province();
			province.setId(cursor.getColumnIndex("id"));
			province.setProvinceName(cursor.getString(cursor
					.getColumnIndex("province_name")));
			province.setProvinceCode(cursor.getString(cursor
					.getColumnIndex("province_code")));
			list.add(province);
		}
		return list;
	}

	/**
	 * ��Cityʵ�����浽���ݿ�
	 */
	public void saveCity(City city) {
		if (city != null) {
			ContentValues values = new ContentValues();
			values.put("city_name", city.getCityName());
			values.put("city_code", city.getCityCode());
			values.put("province_id", city.getProvinceId());
			db.insert(TABLE_CITY, null, values);
		}
	}

	/**
	 * �����ݿ��ж�ȡĳʡ�µ����г���
	 * @return 
	 */
	public List<City> loadCities(int provinceId) {
		Cursor cursor =db.query(TABLE_CITY, null, "province_id=?", new String[] { String.valueOf(provinceId) }, null, null, null);
		List<City> list =new ArrayList<City>();
		while (cursor.moveToFirst()) {
			City city = new City();
			city.setId(cursor.getColumnIndex("id"));
			city.setCityName(cursor.getString(cursor
					.getColumnIndex("city_name")));
			city.setCityCode(cursor.getString(cursor
					.getColumnIndex("city_code")));
			city.setProvinceId(provinceId);
			list.add(city);
		}
		return list;
	}
	/**
	 * ��Countyʵ�����浽���ݿ�
	 */
	public void saveCounty(County county){
		if (county!=null) {
			ContentValues values = new ContentValues();
			values.put("county_name", county.getCountyName());
			values.put("county_code", county.getCountyCode());
			values.put("city_id", county.getCityId());
			db.insert(TABLE_COUNTY, null, values);
		}
		
	}
	/**
	 * �����ݿ��ȡĳ���������е��ص���Ϣ
	 * @return 
	 */
	public List<County> loadCountyties(int cityId){
		Cursor  cursor = db.query(TABLE_COUNTY, null, "city_id=?", new String[]{String.valueOf(cityId)}, null, null, null);
		List<County> list =new ArrayList<County>();
		while (cursor.moveToFirst()) {
			County county = new County();
			county.setId(cursor.getColumnIndex("id"));
			county.setCountyName(cursor.getString(cursor
					.getColumnIndex("county_name")));
			county.setCountyCode(cursor.getString(cursor
					.getColumnIndex("county_code")));
			county.setCityId(cityId);
			list.add(county);
		}
		return list;
	}

}
