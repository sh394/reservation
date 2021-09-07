package com.duke.booking.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.duke.booking.dto.Category;

@Repository
public class CategoryDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);
	private static final String SELECT_ALL =
		      "SELECT category.id as id, category.name as name, "
		      + "count(*) as count FROM category LEFT JOIN product ON category.id = product.category_id "
		      + "LEFT JOIN display_info ON product.id = display_info.product_id GROUP BY category.name";
	
	public CategoryDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<Category> selectAll() {
		return jdbc.query(SELECT_ALL, rowMapper);
	}

}
