package com.marondal.test.jstl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marondal.test.jstl.bo.WeatherBO;
import com.marondal.test.jstl.model.Weather;

@Controller
@RequestMapping("/jstl/weather")
public class WeatherController {
	
	@Autowired
	private WeatherBO weatherBO;
	
	@GetMapping("/list")
	public String weatherList(Model model) {
		
		// 날씨 정보 리스트 
		List<Weather> weatherHistory = weatherBO.getWeatherHistory();
		model.addAttribute("weatherhistory", weatherHistory);
		
		return "jstl/test05/weatherList";
	}
	
	@GetMapping("/input")
	public String weatherInput() {
		
		return "jstl/test05/weatherInput";
	}
	
	// 날짜, 날씨, 기온, 강수량, 미세먼지, 풍속
	@GetMapping("/insert")
	public String addWeather(
			@DateTimeFormat(pattern="yyyy년 MM월 dd일")
			@RequestParam("date") Date date
			, @RequestParam("weather") String weather
			, @RequestParam("temperatures") double temperatures
			, @RequestParam("precipitation") double precipitation
			, @RequestParam("microDust") String microDust
			, @RequestParam("windSpeed") double windSpeed) {
		
		int count = weatherBO.addWeather(date, weather, temperatures, precipitation, microDust, windSpeed);
		
		return "redirect:/jstl/weather/list";
		
	}

}
