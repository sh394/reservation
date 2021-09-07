package com.duke.booking.dao;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import com.duke.booking.dto.ProductPrice;

@Repository
public class ProductPriceDao {
	  private NamedParameterJdbcTemplate jdbc;
	  private RowMapper<ProductPrice> rowMapper = BeanPropertyRowMapper.newInstance(ProductPrice.class);
	  private static final String SELECT_BY_DISPLAY_INFO_ID =
		      "SELECT product_price.id AS product_price_id, product_price.product_id, price_type_name, price, discount_rate, product_price.create_date, product_price.modify_date FROM product_price LEFT JOIN product ON product.id = product_price.product_id LEFT JOIN display_info ON product.id = display_info.product_id WHERE display_info.id = :displayInfoId";


	  public ProductPriceDao(DataSource dataSource) {
	    this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	  }

	  public List<ProductPrice> selectByDisplayInfoId(int displayInfoId) {
	    return jdbc.query(SELECT_BY_DISPLAY_INFO_ID,
	        Collections.singletonMap("displayInfoId", displayInfoId), rowMapper);
	  }
}
