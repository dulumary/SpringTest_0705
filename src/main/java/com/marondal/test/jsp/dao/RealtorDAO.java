package com.marondal.test.jsp.dao;

import org.springframework.stereotype.Repository;

import com.marondal.test.jsp.model.Realtor;

@Repository
public interface RealtorDAO {
	
	public int insertRealtor(Realtor realtor);

}
