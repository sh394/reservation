package com.duke.booking.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import com.duke.booking.util.DateUtil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationParam {
	
	
	@Positive
	private int displayInfoId;
	
	@Positive
	private int productId;
	
	@Email(message = "invalid email format")
	@NotBlank(message="Please enter a valid email address")
	private String reservationEmail;
	
	@NotBlank(message="Please enter a valid name. Reservation name cannot be blank")
	private String reservationName;
	
	@NotBlank(message="Please enter a valid contact number")
	private String reservationTelephone;
	
	@NotBlank(message="Reservation date cannot be blank")
	private String reservationYearMonthDay;
	
	@NotEmpty(message="Please choose your ticket")
	private List<ReservationPrice> prices;
	
	public ReservationInfo getReservationInfo() {
	    String nowDate = DateUtil.getNowDate();

	    return ReservationInfo.builder().productId(this.getProductId())
	        .displayInfoId(this.getDisplayInfoId()).reservationName(this.getReservationName())
	        .reservationEmail(this.getReservationEmail())
	        .reservationTelephone(this.getReservationTelephone())
	        .reservationDate(this.getReservationYearMonthDay()).createDate(nowDate).modifyDate(nowDate)
	        .build();
	}
	
}
