package com.duke.booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duke.booking.dao.ReservationInfoDao;
import com.duke.booking.dao.ReservationPriceDao;
import com.duke.booking.dto.ReservationInfo;
import com.duke.booking.dto.ReservationParam;
import com.duke.booking.dto.ReservationPrice;
import com.duke.booking.dto.ReservationResponse;
import com.duke.booking.util.DateUtil;

@Service
public class DefaultReservationService implements ReservationService {
	  private final ReservationInfoDao reservationInfoDao;
	  private final ReservationPriceDao reservationPriceDao;
	
	  @Autowired
	  public DefaultReservationService(ReservationInfoDao reservationInfoDao, ReservationPriceDao reservationPriceDao) {
		
		  this.reservationInfoDao = reservationInfoDao;
		  this.reservationPriceDao = reservationPriceDao;
	  }
	  @Override
	  @Transactional(readOnly = true)
	  public List<ReservationInfo> getReservationInfoByEmail(String reservationEmail) {
	    return reservationInfoDao.selectByEmail(reservationEmail);
	  }

	  @Override
	  @Transactional(readOnly = true)
	  public int getTotalPrice(String reservationEmail, int productId, int displayInfoId,
	      String reservationDate) {
	    return reservationInfoDao.selectTotalPrice(reservationEmail, productId, displayInfoId,
	        reservationDate);
	  }

	  @Override
	  @Transactional(readOnly = true)
	  public int getIdByEmail(String reservationEmail) {
	    return reservationInfoDao.selectIdByEmail(reservationEmail);
	  }

	  @Override
	  @Transactional(readOnly = false)
	  public ReservationResponse changeCancelFlagById(int reservationInfoId, boolean cancelFlag,
	      String modifyDate) {
	    int updateCnt =
	        reservationInfoDao.updateCancelFlagById(reservationInfoId, cancelFlag, modifyDate);

	    
	    ReservationResponse reservationResponse = ReservationResponse.builder()
	        .reservationInfoId(reservationInfoId).cancelYn(false).createDate(DateUtil.getNowDate())
	        .modifyDate(DateUtil.getNowDate()).displayInfoId(1).productId(1)
	        .reservationDate(DateUtil.getNowDate()).reservationEmail("test@test.com")
	        .reservationInfoId(reservationInfoId).reservationName("test")
	        .reservationTelephone("000-0000-0000").prices(new ArrayList<ReservationPrice>()).build();

	    return reservationResponse;
	  }

	  @Override
	  @Transactional(readOnly = false)
	  public ReservationResponse addReservation(ReservationParam reservationParam) {
	    ReservationInfo reservationInfo = reservationParam.getReservationInfo();

	    int reservationInfoId = reservationInfoDao.insert(reservationInfo);

	    List<ReservationPrice> prices = reservationParam.getPrices();
	    for (ReservationPrice reservationPrice : prices) {
	      reservationPrice.setReservationInfoId(reservationInfoId);
	      Long reservationPriceId = reservationPriceDao.insert(reservationPrice);
	    }

	   
	    ReservationResponse reservationResponse = ReservationResponse.builder()
	        .reservationInfoId(reservationInfoId).cancelYn(false).createDate(DateUtil.getNowDate())
	        .modifyDate(DateUtil.getNowDate()).displayInfoId(1).productId(1)
	        .reservationDate(DateUtil.getNowDate()).reservationEmail("test@test.com")
	        .reservationInfoId(reservationInfoId).reservationName("test")
	        .reservationTelephone("000-0000-0000").prices(new ArrayList<ReservationPrice>()).build();

	    return reservationResponse;
	  }

	  @Override
	  public String getRandomReservationDate() {
	    return DateUtil.getRandomYearMonthDay("yyyy-MM-dd");
	  }
	
}
