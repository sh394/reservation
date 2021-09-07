package com.duke.booking.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;


import com.duke.booking.dto.ReservationInfo;

@Repository
public class ReservationInfoDao {
	 private NamedParameterJdbcTemplate jdbc;
	  private RowMapper<ReservationInfo> rowMapper =
	      BeanPropertyRowMapper.newInstance(ReservationInfo.class);
	  private SimpleJdbcInsert insertAction;
	  public static final String SELECT_BY_EMAIL =
		      "SELECT id AS reservation_info_id, product_id, display_info_id, reservation_name, reservation_tel AS reservation_telephone, reservation_email, cancel_flag AS cancel_yn, create_date, modify_date, reservation_date FROM reservation_info WHERE reservation_email = :reservationEmail";

	  private static final String SELECT_TOTAL_PRICE =
		      "SELECT sum(price*count) FROM reservation_info LEFT JOIN reservation_info_price ON reservation_info.id = reservation_info_price.reservation_info_id LEFT JOIN product_price ON reservation_info_price.product_price_id = product_price.id WHERE reservation_email = :reservationEmail AND reservation_info.product_id = :productId AND reservation_info.display_info_id = :displayInfoId AND reservation_date = :reservationDate";

	  private static final String SELECT_ID_BY_EMAIL =
		      "SELECT id FROM reservation_info WHERE reservation_email = :reservationEmail";

	  private static final String UPDATE_CANCEL_FLAG_BY_ID =
		      "UPDATE reservation_info SET cancel_flag = :cancelFlag, modify_date = :modifyDate WHERE id = :reservationInfoId";

	  public ReservationInfoDao(DataSource dataSource) {
	    this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	    this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("reservation_info")
	        .usingGeneratedKeyColumns("id").usingColumns("product_id", "display_info_id",
	            "reservation_name", "reservation_tel", "reservation_email", "reservation_date",
	            "create_date", "modify_date");

	  }

	  public List<ReservationInfo> selectByEmail(String reservationEmail) {
	    return jdbc.query(SELECT_BY_EMAIL,
	        Collections.singletonMap("reservationEmail", reservationEmail), rowMapper);
	  }

	  public int selectTotalPrice(String reservationEmail, int productId, int displayInfoId,
	      String reservationDate) {
	    int totalPrice;

	    try {
	      Map<String, Object> params = new HashMap<>();
	      params.put("reservationEmail", reservationEmail);
	      params.put("productId", productId);
	      params.put("displayInfoId", displayInfoId);
	      params.put("reservationDate", reservationDate);

	      totalPrice =
	          jdbc.queryForObject(SELECT_TOTAL_PRICE, params, Integer.class);
	    } catch (NullPointerException e) {
	      totalPrice = 0;
	    } catch (Exception e) {
	      totalPrice = 0;
	    }

	    return totalPrice;
	  }

	  public int selectIdByEmail(String reservationEmail) {
	    int id;

	    try {
	      id = jdbc.queryForObject(SELECT_ID_BY_EMAIL,
	          Collections.singletonMap("reservationEmail", reservationEmail), Integer.class);
	    } catch (NullPointerException e) {
	      id = 0;
	    } catch (Exception e) {
	      id = 0;
	    }

	    return id;
	  }

	  public int updateCancelFlagById(int reservationInfoId, boolean cancelFlag, String modifyDate) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("reservationInfoId", reservationInfoId);
	    params.put("cancelFlag", cancelFlag);
	    params.put("modifyDate", modifyDate);

	    return jdbc.update(UPDATE_CANCEL_FLAG_BY_ID, params);
	  }

	  public int insert(ReservationInfo reservationInfo) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("product_id", reservationInfo.getProductId());
	    params.put("display_info_id", reservationInfo.getDisplayInfoId());
	    params.put("reservation_name", reservationInfo.getReservationName());
	    params.put("reservation_tel", reservationInfo.getReservationTelephone());
	    params.put("reservation_email", reservationInfo.getReservationEmail());
	    params.put("reservation_date", reservationInfo.getReservationDate());
	    params.put("create_date", reservationInfo.getCreateDate());
	    params.put("modify_date", reservationInfo.getModifyDate());

	    return insertAction.executeAndReturnKey(params).intValue();
	  }
	  
}
