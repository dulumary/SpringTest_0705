package com.marondal.test.database.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marondal.test.database.dao.StoreDAO;
import com.marondal.test.database.model.Store;

@Service
public class StoreBO {
	
	@Autowired
	private StoreDAO storeDAO;
	
	// store 테이블 전체 데이터 조회
	public List<Store> getStoreList() {
		List<Store> storeList = storeDAO.selectStoreList();
		
		return storeList;
		
	}

}
