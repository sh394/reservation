package com.duke.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationInfo {
	  @NonNull
	  private String reservationName;

	  @NonNull
	  private String reservationEmail;

	  @NonNull
	  private String reservationTelephone;

	  @NonNull
	  private String reservationDate;
	  
	  @NonNull
	  private String createDate;

	  @NonNull
	  private String modifyDate;

	 
	  private int productId;

	  
	  @Builder.Default
	  private int totalPrice = 0;

	  
	  private int displayInfoId;

	  private int reservationInfoId;

	  
	  @Builder.Default
	  private boolean cancelYn = false;

	  private DisplayInfo displayInfo;
}
