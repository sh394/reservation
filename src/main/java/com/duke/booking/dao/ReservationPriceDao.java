package com.duke.booking.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.duke.booking.dto.ReservationPrice;

@Repository
public class ReservationPriceDao {
	  private SimpleJdbcInsert insertAction;

	  public ReservationPriceDao(DataSource dataSource) {
	    this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("reservation_info_price")
	        .usingGeneratedKeyColumns("id");
	  }

	  public Long insert(ReservationPrice reservationPrice) {
	    SqlParameterSource params = new BeanPropertySqlParameterSource(reservationPrice);
	    return insertAction.executeAndReturnKey(params).longValue();
	  }
}
