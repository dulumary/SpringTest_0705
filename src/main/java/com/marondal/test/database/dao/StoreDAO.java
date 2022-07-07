package com.marondal.test.database.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.marondal.test.database.model.Store;

@Repository
public interface StoreDAO {
	
	// store 테이블 데이터 모두 가져오기 
	public List<Store> selectStoreList();

}
