package com.marondal.test.mybatis.bo;

import java.util.List;

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
	
	// 전달 받은 rentPrice 보다 적은 월세의 매물 조회 
	public List<RealEstate> getRealEstateByRentPrice(int rentPrice) {
		List<RealEstate> list = realEstateDAO.selectRealEstateByRentPrice(rentPrice);
		return list;
	}
	
	public List<RealEstate> getRealEstateByAreaPrice(int area, int price) {
		  return  realEstateDAO.selectRealEstateByAreaPrice(area, price);
	}

}
