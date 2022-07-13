package com.marondal.test.jsp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.marondal.test.jsp.bo.SellerBO;
import com.marondal.test.jsp.model.Seller;

@Controller
@RequestMapping("/jsp/seller")
public class SellerController {
	
	@Autowired
	private SellerBO sellerBO;
	
	@PostMapping("/insert")
	@ResponseBody
	public String addSeller(
			@RequestParam("nickname") String nickName
			, @RequestParam("profileUrl") String profileUrl
			, @RequestParam("temperature") double temperature) {
		
		int count = sellerBO.addSeller(nickName, profileUrl, temperature);
		
		return "삽입 결과 : " + count;
		
	}
	
	@GetMapping("/input")
	public String sellerInput() {
		
		return "jsp/sellerInput";
	}
	
	@GetMapping("/info")
	public String sellerInfo(
			@RequestParam(value="id", required=false) Integer id
			, Model model) {
		
		// 비필수 파라미터 
		// 값이 전달되면 그 값으로 셋팅
		// 값이 전달이 안되면 null
//		Seller seller = null;
//		if(id == null) {			
//			seller = sellerBO.getLastSeller();
//			
//		} else {
//			seller = sellerBO.getSeller(id);
//		}
		
		Seller seller = sellerBO.getSeller(id);
		model.addAttribute("result", seller);
		
		return "jsp/sellerInfo";
	}
	
	

}
