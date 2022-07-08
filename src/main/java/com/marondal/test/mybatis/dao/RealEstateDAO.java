package com.marondal.test.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.marondal.test.mybatis.model.RealEstate;

@Repository
public interface RealEstateDAO {
	
	// id가 5인 real estate 정보 전달
	public RealEstate selectRealEstate(@Param("id") int id);
	
	// 전달 받은 rentPrice 보다 적은 월세 매물 조회
	public List<RealEstate> selectRealEstateByRentPrice(@Param("rentPrice") int rentPrice);
	
	public List<RealEstate> selectRealEstateByAreaPrice(
			@Param("area") int area
			,@Param("price") int price);

}
