package com.duke.booking.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;


import com.duke.booking.dto.CommentImage;

@Repository
public class CommentImageDao {
	  private NamedParameterJdbcTemplate jdbc;
	  private RowMapper<CommentImage> rowMapper = BeanPropertyRowMapper.newInstance(CommentImage.class);
	  private SimpleJdbcInsert insertAction;
	  
	  private static final String SELECT_COMMENT_IMAGES =
		      "SELECT reservation_user_comment_image.id as image_id, "
		      + "reservation_user_comment_image.reservation_info_id, "
		      + "reservation_user_comment_image.reservation_user_comment_id, "
		      + "file_info.id AS file_id, file_name, save_file_name, content_type, delete_flag, "
		      + "file_info.create_date, file_info.modify_date FROM reservation_user_comment_image "
		      + "LEFT JOIN reservation_info ON reservation_user_comment_image.reservation_info_id = reservation_info.id "
		      + "LEFT JOIN file_info ON reservation_user_comment_image.file_id = file_info.id "
		      + "WHERE reservation_info.display_info_id = :displayInfoId AND reservation_user_comment_image.reservation_user_comment_id = :commentId";

	  public CommentImageDao(DataSource dataSource) {
	    this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	    this.insertAction = new SimpleJdbcInsert(dataSource)
	        .withTableName("reservation_user_comment_image").usingGeneratedKeyColumns("id");
	  }

	  public List<CommentImage> selectByDisplayInfoId(int displayInfoId, int commentId) {
	    Map<String, Integer> params = new HashMap<>();
	    params.put("displayInfoId", displayInfoId);
	    params.put("commentId", commentId);

	    return jdbc.query(SELECT_COMMENT_IMAGES, params, rowMapper);
	  }

	   public int insert(int reservationInfoId, int commentId, int fileId) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("reservation_info_id", reservationInfoId);
	    params.put("reservation_user_comment_id", commentId);
	    params.put("file_id", fileId);

	    return insertAction.executeAndReturnKey(params).intValue();
	   }
}
