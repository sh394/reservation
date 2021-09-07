package com.duke.booking.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.duke.booking.dao.ProductDao.ImageType;

import com.duke.booking.dto.Promotion;

@Repository
public class PromotionDao {
	  private NamedParameterJdbcTemplate jdbc;
	  private RowMapper<Promotion> rowMapper = BeanPropertyRowMapper.newInstance(Promotion.class);
	  private static final String SELECT_ALL =
			      "SELECT promotion.id, promotion.product_id, file_info.save_file_name as productImageUrl, product_image.id as product_image_id FROM promotion LEFT JOIN product_image ON promotion.product_id = product_image.product_id LEFT JOIN file_info ON product_image.file_id = file_info.id WHERE product_image.type = :imageType";
		


	  public PromotionDao(DataSource dataSource) {
	      this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	  }

	  public List<Promotion> selectAll() {
	      Map<String, String> params = new HashMap<>();
	      params.put("imageType", ImageType.THUMBNAIL.getImageTypeName());

	      return jdbc.query(SELECT_ALL, params, rowMapper);
	  }
}
