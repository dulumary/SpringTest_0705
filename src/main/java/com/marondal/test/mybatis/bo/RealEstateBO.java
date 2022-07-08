package com.marondal.test.mybatis.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marondal.test.mybatis.dao.RealEstateDAO;
import com.marondal.test.mybatis.model.RealEstate;

@Service
public class RealEstateBO {
	
	@Autowired
	private RealEstateDAO realEstateDAO;
	
	// id가 5인 real estate 정보 전달
	public RealEstate getRealEstate(int id) {
		 RealEstate realEstate = realEstateDAO.selectRealEstate(id);
		 
		 return realEstate;
	}

}
