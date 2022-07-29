package com.marondal.test.ajax;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.marondal.test.ajax.bo.BookingBO;
import com.marondal.test.ajax.model.Booking;

@Controller
@RequestMapping("/ajax/booking")
public class BookingController {
	
	@Autowired
	private BookingBO bookingBO;
	
	@GetMapping("/main")
	public String bookingMain() {
		return "ajax/bookingMain";
	}
	
	
	@GetMapping("/list")
	public String bookingList(Model model) {
		
		List<Booking> bookingList = bookingBO.getBookingList();
		
		model.addAttribute("bookingList", bookingList);
		
		return "ajax/bookingList";
	}
	// 예약 삭제 api
	// {"result":"success"} {"result":"fail"}
	@GetMapping("/delete")
	@ResponseBody
	public Map<String, String> deleteBooking(@RequestParam("id") int id) {
		
		int count = bookingBO.deleteBooking(id);
		
		Map<String, String> map = new HashMap<>();
		
		if(count == 1) {
			map.put("result", "success");
		} else {
			map.put("result", "fail");
		}
		
		return map;
		
	}
	
	
	// 예약 데이터 저장 기능 api
	// {"result":"success"} {"result":"fail"}
	@GetMapping("/insert")
	@ResponseBody
	public Map<String, String> addBooking(
			@RequestParam("name") String name,
			@DateTimeFormat(pattern="yyyy년 MM월 dd일")
			@RequestParam("date") Date date,
			@RequestParam("day") int day,
			@RequestParam("headcount") int headcount,
			@RequestParam("phoneNumber") String phoneNumber) {
		
		int count = bookingBO.addBooking(name, date, day, headcount, phoneNumber);
		
		Map<String, String> map = new HashMap<>();
		
		if(count == 1) {
			map.put("result", "success");
		} else {
			map.put("result", "fail");
		}
		
		return map;
		
	}
	
	
	
	@GetMapping("/input")
	public String bookingInput() {
		return "ajax/bookingInput";
	}
	
	
	
	// 이름과 전화번호가 일치하는 예약 정보 얻어 오기 api
	// 조회된 결과 이름 : 김인규 날짜 : 2022년 7월 29일 숙박일수 : 3 ~~~
	// {"name":"김인규", "date":"2022-07-29", "day":3....}
	@GetMapping("/get")
	@ResponseBody
	public Booking getBooking(
			@RequestParam("name") String name
			, @RequestParam("phoneNumber") String phoneNumber) {
		
		Booking booking = bookingBO.getBooking(name, phoneNumber);
		
		return booking;
		
	}
	
	
	
	
	
}
