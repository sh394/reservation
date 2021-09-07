package com.duke.booking.dao;

import java.util.Collections;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import com.duke.booking.dto.DisplayInfoImage;

@Repository
public class DisplayInfoImageDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<DisplayInfoImage> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfoImage.class);
	private static final String SELECT_BY_DISPLAY_INFO_ID =
		      "SELECT display_info_image.id AS display_info_image_id, display_info_id, "
		      + "file_info.id AS file_id, file_name, save_file_name, content_type, "
		      + "delete_flag, create_date, modify_date FROM display_info_image "
		      + "LEFT JOIN file_info ON file_id = file_info.id WHERE display_info_id = :displayInfoId";
	
	public DisplayInfoImageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public DisplayInfoImage selectByDisplayInfoId(int displayInfoId) {
	    DisplayInfoImage displayInfoImage;

	    try {
	      displayInfoImage = jdbc.queryForObject(SELECT_BY_DISPLAY_INFO_ID,
	          Collections.singletonMap("displayInfoId", displayInfoId), rowMapper);
	    } catch (EmptyResultDataAccessException e) {
	      displayInfoImage = null;
	    } catch (Exception e) {
	      
	      displayInfoImage = null;
	    }

	    return displayInfoImage;
	  }
}
