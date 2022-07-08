package com.marondal.test.mybatis.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.marondal.test.mybatis.model.RealEstate;

@Repository
public interface RealEstateDAO {
	
	// id가 5인 real estate 정보 전달
	public RealEstate selectRealEstate(@Param("id") int id);

}
