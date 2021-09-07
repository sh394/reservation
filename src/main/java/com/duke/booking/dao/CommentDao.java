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


import com.duke.booking.dto.Comment;
import com.duke.booking.dto.CommentParam;
import com.duke.booking.util.DateUtil;




@Repository
public class CommentDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Comment> rowMapper = BeanPropertyRowMapper.newInstance(Comment.class);
	private SimpleJdbcInsert insertAction;
	
	private static final String SELECT_COMMENTS_BY_DISPLAY_INFO_ID =
		      "SELECT reservation_user_comment.id AS comment_id, reservation_user_comment.product_id, "
		      + "reservation_user_comment.reservation_info_id AS reservation_info_id, score, comment, "
		      + "reservation_name, reservation_tel AS reservation_telephone, reservation_email, "
		      + "reservation_date, reservation_user_comment.create_date, "
		      + "reservation_user_comment.modify_date FROM reservation_user_comment "
		      + "LEFT JOIN reservation_info ON "
		      + "reservation_info.id = reservation_user_comment.reservation_info_id "
		      + "WHERE reservation_info.display_info_id = :displayInfoId ORDER BY create_date DESC";

	private static final String SELECT_AVG_SCORE_BY_DISPLAY_INFO_ID =
		      "SELECT AVG(score) FROM reservation_user_comment "
		      + "LEFT JOIN reservation_info ON reservation_info.id = reservation_user_comment.reservation_info_id "
		      + "WHERE reservation_info.display_info_id = :displayInfoId";
	
	public CommentDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("reservation_user_comment").usingGeneratedKeyColumns("id");
		
	}
	
	public List<Comment> selectByDisplayInfoId(int displayInfoId) {
		return jdbc.query(SELECT_COMMENTS_BY_DISPLAY_INFO_ID,
				Collections.singletonMap("displayInfoId", displayInfoId), rowMapper);
	}
	
	public double selectAvgScoreByDisplayInfoId(int displayInfoId) {
		double avgScore;
		
		try {
			avgScore = jdbc.queryForObject(SELECT_AVG_SCORE_BY_DISPLAY_INFO_ID,
					Collections.singletonMap("displayInfoId", displayInfoId), Double.class);
			
		} catch(NullPointerException e) {
			avgScore = 0.0;
		} catch(Exception e) {
			
			avgScore = 0.0;
		}
		
		return avgScore;
	}
	
	public int insert(CommentParam commentParam) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("product_id", commentParam.getProductId());
	    params.put("reservation_info_id", commentParam.getReservationInfoId());
	    params.put("score", commentParam.getScore());
	    params.put("comment", commentParam.getComment());
	    params.put("create_date", DateUtil.getNowDate());
	    params.put("modify_date", DateUtil.getNowDate());

	    return insertAction.executeAndReturnKey(params).intValue();
	  }
}
