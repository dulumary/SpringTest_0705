package com.marondal.test.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.marondal.test.database.bo.StoreBO;
import com.marondal.test.database.model.Store;

@Controller
public class StoreController {
	
	@Autowired
	private StoreBO storeBO;
	
	@RequestMapping("/database/test01/1")
	@ResponseBody
	public List<Store> storeList() {
		// store 테이블의 모든 행을 json 으로 response에 전달한다. 
		List<Store> storeList = storeBO.getStoreList();
		
		return storeList;
		
	}

}
