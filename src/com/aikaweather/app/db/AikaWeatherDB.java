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
	 * 数据库名
	 */
	public static final String DB_NAME = "aika_weather";
	/**
	 * 表名
	 */
	public String TABLE_PROVINCE = "Province";
	public String TABLE_CITY = "City";
	public String TABLE_COUNTY = "County";
	/**
	 * 数据库版本
	 */
	private static final int VERAION = 1;
	private static AikaWeatherDB aikaWeatherDB;
	private SQLiteDatabase db;

	/**
	 * 重写构造方法
	 */
	public AikaWeatherDB(Context context) {
		AikaWeatherOpenHelper dbHelper = new AikaWeatherOpenHelper(context, DB_NAME, null, VERAION);
		db = dbHelper.getReadableDatabase();
	}

	/**
	 * 获得AikaWeatherDB类的示例
	 */
	public synchronized static AikaWeatherDB getInstance(Context context) {
		if (aikaWeatherDB == null) {
			aikaWeatherDB = new AikaWeatherDB(context);
		}
		return aikaWeatherDB;
	}

	/**
	 * 将Province示例存储到数据库
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
	 * 从数据库读取全国所有的省份信息
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
	 * 将City实例储存到数据库
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
	 * 从数据库中读取某省下的所有城市
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
	 * 将County实例储存到数据库
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
	 * 从数据库读取某城市下所有的县的信息
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
