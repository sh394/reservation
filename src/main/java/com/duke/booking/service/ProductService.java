package com.duke.booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.duke.booking.dto.FileInfo;
import com.duke.booking.dto.Product;
import com.duke.booking.dto.ProductImage;
import com.duke.booking.dto.ProductPrice;

@Service
public interface ProductService {
	  public static final Integer LIMIT = 4;

	  public List<Product> getProductsAll(int start);

	  public List<Product> getProductsByCategoryId(int categoryId, int start);

	  public int getProductCountAll();

	  public int getProductCountByCategoryId(int categoryId);

	  List<ProductImage> getImagesByDisplayInfoId(int displayInfoId);

	  List<ProductPrice> getPricesByDisplayInfoId(int displayInfoId);

	  public FileInfo getProductFileInfoByProductImgId(int productImageId);

}
