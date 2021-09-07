package com.duke.booking.dao;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import com.duke.booking.dto.ProductImage;

@Repository
public class ProductImageDao {
	  private NamedParameterJdbcTemplate jdbc;
	  private RowMapper<ProductImage> rowMapper = BeanPropertyRowMapper.newInstance(ProductImage.class);
	  

	  private static final String SELECT_BY_DISPLAY_INFO_ID =
		      "SELECT content_type, file_info.create_date, delete_flag, file_info.id AS file_info_id, file_name, file_info.modify_date, product_image.product_id, product_image.id AS product_image_id, save_file_name, type FROM product_image LEFT JOIN file_info ON product_image.file_id = file_info.id LEFT JOIN display_info ON product_image.product_id = display_info.product_id WHERE display_info.id = :displayInfoId";
		

	  public ProductImageDao(DataSource dataSource) {
	    this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	  }

	  public List<ProductImage> selectByDisplayInfoId(int displayInfoId) {
	    return jdbc.query(SELECT_BY_DISPLAY_INFO_ID,
	        Collections.singletonMap("displayInfoId", displayInfoId), rowMapper);
	  }
}
