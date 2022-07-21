package com.marondal.test.jstl.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.marondal.test.jstl.model.Weather;

@Repository
public interface WeatherDAO {
	
	// 날씨 정보 모두 가져오기 
	public List<Weather> selectWeatherHistory();
	
	// 날씨 정보 삽입
	public int insertWeather(
			@Param("date") Date date
			, @Param("weather") String weather
			, @Param("temperatures") double temperatures
			, @Param("precipitation") double precipitation
			, @Param("microDust") String microDust
			, @Param("windSpeed") double windSpeed);

}
