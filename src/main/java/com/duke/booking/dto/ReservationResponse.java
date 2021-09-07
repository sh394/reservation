package com.duke.booking.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationResponse {
	  
	  private boolean cancelYn;

	  
	  private int displayInfoId;
	  
	  
	  private int reservationInfoId;
	  
	  
	  private int productId;

	  @NonNull
	  private String modifyDate;
	  
	  @NonNull
	  private String createDate;

	  @NonNull
	  private String reservationDate;

	  @NonNull
	  private String reservationEmail;

	 
	  @NonNull
	  private String reservationName;

	  @NonNull
	  private String reservationTelephone;

	  @NonNull
	  private List<ReservationPrice> prices;
}
