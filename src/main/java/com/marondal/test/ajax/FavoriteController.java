package com.marondal.test.ajax;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.marondal.test.ajax.bo.FavoriteBO;
import com.marondal.test.ajax.model.Favorite;

@Controller
public class FavoriteController {
	
	@Autowired
	private FavoriteBO favoriteBO;
	
	// 리스트 페이지 
	@GetMapping("/ajax/favorite/list")
	public String favoriteList(Model model) {
		
		List<Favorite> favoriteList = favoriteBO.getFavoriteList();
		
		model.addAttribute("favoriteList", favoriteList);
		
		return "ajax/favoriteList";
	}

}
