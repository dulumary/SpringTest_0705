package com.marondal.test.ajax.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.marondal.test.ajax.model.Booking;

@Repository
public interface BookingDAO {
	
	public List<Booking> selectBookingList();

}
