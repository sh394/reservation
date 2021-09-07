package com.duke.booking.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import com.duke.booking.dto.Product;

@Repository
public class ProductDao {
	  private NamedParameterJdbcTemplate jdbc;
	  private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);
	  
	  private static final String SELECT_PRODUCTS_ALL =
		      "SELECT product.id as product_id, product.description as product_description, product.content as product_content, display_info.id as display_info_id, display_info.place_name as place_name, file_info.save_file_name as product_image_url, product_image.id as product_image_id FROM product LEFT JOIN display_info ON product.id = display_info.product_id LEFT JOIN product_image ON product.id = product_image.product_id LEFT JOIN file_info ON product_image.file_id = file_info.id WHERE product_image.type = :imageType ORDER BY product.id limit :start, :limit";

	  private static final String SELECT_PRODUCTS_BY_CATEGORY_ID =
		      "SELECT product.id as product_id, product.description as product_description, product.content as product_content, display_info.id as display_info_id, display_info.place_name as place_name, file_info.save_file_name as product_image_url, product_image.id as product_image_id FROM product LEFT JOIN display_info ON product.id = display_info.product_id LEFT JOIN product_image ON product.id = product_image.product_id LEFT JOIN file_info ON product_image.file_id = file_info.id WHERE product.category_id = :categoryId AND product_image.type = :imageType ORDER BY product.id limit :start, :limit";

	  private static final String SELECT_COUNT_ALL =
	 	      "SELECT count(*) FROM product LEFT JOIN display_info ON product.id = display_info.product_id ";

	  private static final String SELECT_COUNT_BY_CATEGORY_ID =
		      "SELECT count(*) FROM product LEFT JOIN display_info ON product.id = display_info.product_id WHERE product.category_id = :categoryId";

	  public ProductDao(DataSource dataSource) {
	    this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	  }

	  public List<Product> selectProductsAll(int start, int limit) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("start", start);
	    params.put("limit", limit);
	    params.put("imageType", ImageType.THUMBNAIL.getImageTypeName());

	    return jdbc.query(SELECT_PRODUCTS_ALL, params, rowMapper);
	  }

	  public List<Product> selectProductsByCategoryId(int categoryId, int start, int limit) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("categoryId", categoryId);
	    params.put("start", start);
	    params.put("limit", limit);
	    params.put("imageType", ImageType.THUMBNAIL.getImageTypeName());

	    return jdbc.query(SELECT_PRODUCTS_BY_CATEGORY_ID, params, rowMapper);
	  }

	  public int selectCountAll() {
	    return jdbc.queryForObject(SELECT_COUNT_ALL, Collections.emptyMap(), int.class);
	  }

	  public int selectCountByCategoryId(int categoryId) {
	    return jdbc.queryForObject(SELECT_COUNT_BY_CATEGORY_ID,
	        Collections.singletonMap("categoryId", categoryId), int.class);
	  }
	  
	  
	  public enum ImageType {
		  THUMBNAIL("th"), MAIN("ma"), ETC("et");

		  private final String imageTypeName;

		  ImageType(String imageTypeName) {
		    this.imageTypeName = imageTypeName;
		  }

		  public String getImageTypeName() {
		    return this.imageTypeName;
		  }

		  public static boolean hasImageTypeName(String imageTypeName) {
		    ImageType[] imageTypes = ImageType.values();
		    for (ImageType imageType : imageTypes) {
		      if (imageType.getImageTypeName().equals(imageTypeName)) {
		        return true;
		      }
		    }

		    return false;
		  }

		  public static ImageType valueOfImageTypeName(String imageTypeName) {
		    ImageType[] imageTypes = ImageType.values();
		    for (ImageType imageType : imageTypes) {
		      if (imageType.getImageTypeName().equals(imageTypeName)) {
		        return imageType;
		      }
		    }

		    return null;
		  }

		}
}
