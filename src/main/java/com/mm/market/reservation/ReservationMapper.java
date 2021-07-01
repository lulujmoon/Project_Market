package com.mm.market.reservation;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper {

	public int setInsert(ReservationVO reservationVO) throws Exception;
	
	public ReservationVO getSelect(ReservationVO reservationVO) throws Exception;
	
	public int setDelete(ReservationVO reservationVO) throws Exception;
}
