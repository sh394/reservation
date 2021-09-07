package com.duke.booking.dao;

import java.util.Collections;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import com.duke.booking.dto.DisplayInfo;

@Repository
public class DisplayInfoDao {
	  private NamedParameterJdbcTemplate jdbc;
	  private RowMapper<DisplayInfo> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfo.class);
	  private static final String SELECT_BY_ID =
		      "SELECT product.id AS product_id, product.category_id, display_info.id AS display_info_id, "
		      + "category.name AS category_name, description AS product_description, "
		      + "content AS product_content, event AS product_event, opening_hours, place_name, "
		      + "place_lot, place_street, tel AS telephone, homepage, email, display_info.create_date, "
		      + "display_info.modify_date FROM display_info LEFT JOIN product ON display_info.product_id = product.id LEFT JOIN category "
		      + "ON product.category_id = category.id WHERE display_info.id = :displayInfoId";

	  public DisplayInfoDao(DataSource dataSource) {
	    this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	  }

	  public DisplayInfo selectById(int displayInfoId) {
		DisplayInfo displayInfo;

	    try {
	      displayInfo = jdbc.queryForObject(SELECT_BY_ID,
	          Collections.singletonMap("displayInfoId", displayInfoId), rowMapper);
	    } catch (EmptyResultDataAccessException e) {
	      displayInfo = null;
	    } catch (Exception e) {
	      displayInfo = null;
	    }

	    return displayInfo;
	  }
}
