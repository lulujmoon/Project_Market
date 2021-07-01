package com.mm.market.reservation;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class ReservationService {

	@Autowired
	ReservationMapper reservationMapper;

	
	public ReservationVO getSelect(ReservationVO reservationVO) throws Exception{
		return reservationMapper.getSelect(reservationVO);
	}
	
	public int setInsert(ReservationVO reservationVO) throws Exception{
		return reservationMapper.setInsert(reservationVO);
	}
}
