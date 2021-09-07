package com.duke.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationPrice {
	
	private int count;
	
	
	private int productPriceId;
	
	
	private int reservationInfoId;
	
	
	private int reservationInfoPriceId;
}
