package com.duke.booking.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;


import com.duke.booking.dto.FileInfo;

@Repository
public class FileInfoDao {
	  private NamedParameterJdbcTemplate jdbc;
	  private RowMapper<FileInfo> rowMapper = BeanPropertyRowMapper.newInstance(FileInfo.class);
	  private SimpleJdbcInsert insertAction;
	  
	  private static final String SELECT_BY_PRODUCT_IMAGE_ID =
		      "SELECT file_info.id, file_info.file_name, file_info.save_file_name, file_info.content_type, file_info.delete_flag, file_info.create_date, file_info.modify_date FROM file_info LEFT JOIN product_image ON product_image.file_id = file_info.id WHERE product_image.id = :productImageId";

	  private static final String SELECT_BY_DISPLAY_INFO_IMAGE_ID =
		      "SELECT file_info.id, file_info.file_name, file_info.save_file_name, file_info.content_type, file_info.delete_flag, file_info.create_date, file_info.modify_date FROM file_info LEFT JOIN display_info_image ON display_info_image.file_id = file_info.id WHERE display_info_image.id = :displayInfoImageId";

	  private static final String SELECT_BY_COMMENT_IMAGE_ID =
		      "SELECT file_info.id, file_info.file_name, file_info.save_file_name, file_info.content_type, file_info.delete_flag, file_info.create_date, file_info.modify_date FROM file_info LEFT JOIN reservation_user_comment_image ON reservation_user_comment_image.file_id = file_info.id WHERE reservation_user_comment_image.id = :commentImageId";

	  public FileInfoDao(DataSource dataSource) {
	    this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	    this.insertAction =
	        new SimpleJdbcInsert(dataSource).withTableName("file_info").usingGeneratedKeyColumns("id");
	  }

	  public int insert(FileInfo fileInfo) {
	    SqlParameterSource params = new BeanPropertySqlParameterSource(fileInfo);
	    return insertAction.executeAndReturnKey(params).intValue();
	  }

	  public FileInfo selectByProductImageId(int productImageId) {
	    FileInfo fileInfo;

	    try {
	      Map<String, Object> params = new HashMap<>();
	      params.put("productImageId", productImageId);

	      fileInfo = jdbc.queryForObject(SELECT_BY_PRODUCT_IMAGE_ID, params, rowMapper);
	    } catch (EmptyResultDataAccessException e) {
	      fileInfo = null;
	    } catch (Exception e) {
	      fileInfo = null;
	    }

	    return fileInfo;
	  }

	  public FileInfo selectByDisplayInfoImageId(int displayInfoImageId) {
	    FileInfo fileInfo;

	    try {
	      Map<String, Object> params = new HashMap<>();
	      params.put("displayInfoImageId", displayInfoImageId);

	      fileInfo =
	          jdbc.queryForObject(SELECT_BY_DISPLAY_INFO_IMAGE_ID, params, rowMapper);
	    } catch (EmptyResultDataAccessException e) {
	      fileInfo = null;
	    } catch (Exception e) {
	      fileInfo = null;
	    }

	    return fileInfo;
	  }

	  public FileInfo selectByCommentImageId(int commentImageId) {
	    FileInfo fileInfo;

	    try {
	      Map<String, Object> params = new HashMap<>();
	      params.put("commentImageId", commentImageId);

	      fileInfo = jdbc.queryForObject(SELECT_BY_COMMENT_IMAGE_ID, params, rowMapper);
	    } catch (EmptyResultDataAccessException e) {
	      fileInfo = null;
	    } catch (Exception e) {
	      fileInfo = null;
	    }

	    return fileInfo;
	  }
}
