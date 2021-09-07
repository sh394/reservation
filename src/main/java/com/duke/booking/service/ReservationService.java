package com.duke.booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.duke.booking.dto.ReservationInfo;
import com.duke.booking.dto.ReservationParam;
import com.duke.booking.dto.ReservationResponse;

@Service
public interface ReservationService {
	  public List<ReservationInfo> getReservationInfoByEmail(String reservationEmail);

	  public int getTotalPrice(String reservationEmail, int productId, int displayInfoId,
	      String reservationDate);

	  public int getIdByEmail(String reservationEmail);

	  public ReservationResponse changeCancelFlagById(int reservationInfoId, boolean cancelFlag,
	      String modifyDate);

	  public ReservationResponse addReservation(ReservationParam reservationParam);

	  public String getRandomReservationDate();
}
