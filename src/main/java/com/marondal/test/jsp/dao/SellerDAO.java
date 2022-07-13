package com.marondal.test.jsp.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.marondal.test.jsp.model.Seller;

@Repository
public interface SellerDAO {
	
	public int insertSeller(
			@Param("nickname") String nickName
			,@Param("profileUrl") String profileUrl
			,@Param("temperature") double temperature); 
	
	public Seller selectLastSeller();
	
	public Seller selectSeller(@Param("id") int id);

}
