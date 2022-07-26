package com.marondal.test.ajax;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.marondal.test.ajax.bo.FavoriteBO;
import com.marondal.test.ajax.model.Favorite;

@Controller
@RequestMapping("/ajax/favorite")
public class FavoriteController {
	
	@Autowired
	private FavoriteBO favoriteBO;
	
	// 리스트 페이지 
	@GetMapping("/list")
	public String favoriteList(Model model) {
		
		List<Favorite> favoriteList = favoriteBO.getFavoriteList();
		
		model.addAttribute("favoriteList", favoriteList);
		
		return "ajax/favoriteList";
	}
	
	// 즐겨 찾기 추가
	@PostMapping("/insert")
	@ResponseBody
	public Map<String, String> addFavorite(
			@RequestParam("name") String name
			, @RequestParam("url") String url) {
		
		
		int count = favoriteBO.addFavorite(name, url);
		
		// 성공시에 {"result":"success"}
		// 실패시에 {"result":"fail"}
		
		Map<String, String> map = new HashMap<>();
		if(count == 1) {
			map.put("result", "success");
		} else {
			map.put("result", "fail");
		}
		
		return map;
		
	}
	
	@GetMapping("/input")
	public String favoriteInput() {
		return "ajax/favoriteInput";
	}
	
	
	// url을 전달 받고, 해당 url 이 이미 테이블에 저장되어 있는지 확인하고 
	// 그 결과를 json 형태로 response에 담아 전달하는 API를 생성
	// {"is_duplicate":true} , {"is_duplicate":false}
	@PostMapping("/is_duplicate")
	@ResponseBody
	public Map<String, Boolean> isDuplicateUrl(
			@RequestParam("url") String url) {
		
		Map<String, Boolean> map = new HashMap<>();
		
		if(favoriteBO.isDuplicateByUrl(url)) {  // url 이 중복된 상태 
//			{"is_duplicate":true}
			map.put("is_duplicate", true);
		} else {  // 중복되지 않은상태 
//			{"is_duplicate":false}
			map.put("is_duplicate", false);
		}
		
		return map;
		
	}
	
	// id를 전달 받고 해당 행을 삭제하는 api
	@GetMapping("/delete")
	@ResponseBody
	public Map<String, String> deleteFavorite(@RequestParam("id") int id) {
		
		int count = favoriteBO.deleteFavorite(id);
		Map<String, String> map = new HashMap<>();
		// {"result":"success"}
		if(count == 1) {  // 성공
			map.put("result", "success");
		} else { // 실패  {"result":"fail"}
			map.put("result", "fail");
		}
		
		return map;
		
	}
	
	
	
	
	

}
