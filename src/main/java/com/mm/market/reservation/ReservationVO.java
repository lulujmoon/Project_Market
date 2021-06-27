package com.mm.market.reservation;

import lombok.Data;

@Data
public class ReservationVO {
	
	private Long reservationNum;
	private Long productNum;
	private String seller;
	private String buyer;
	private Long locationCode;

}