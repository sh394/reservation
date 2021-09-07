package com.duke.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duke.booking.dao.FileInfoDao;
import com.duke.booking.dao.ProductDao;
import com.duke.booking.dao.ProductImageDao;
import com.duke.booking.dao.ProductPriceDao;
import com.duke.booking.dto.FileInfo;
import com.duke.booking.dto.Product;
import com.duke.booking.dto.ProductImage;
import com.duke.booking.dto.ProductPrice;

@Service
public class DefaultProductService implements ProductService{
	  private final ProductDao productDao;
	  private final ProductImageDao productImageDao;
	  private final ProductPriceDao productPriceDao;
	  private final FileInfoDao fileInfoDao;
	
	  @Autowired
	  public DefaultProductService(ProductDao productDao, ProductImageDao productImageDao,
			ProductPriceDao productPriceDao, FileInfoDao fileInfoDao) {
		
		this.productDao = productDao;
		this.productImageDao = productImageDao;
		this.productPriceDao = productPriceDao;
		this.fileInfoDao = fileInfoDao;
	  }
	
	  @Override
	  @Transactional(readOnly = true)
	  public List<Product> getProductsAll(int start) {
	    return productDao.selectProductsAll(start, ProductService.LIMIT);
	  }

	  @Override
	  @Transactional(readOnly = true)
	  public List<Product> getProductsByCategoryId(int categoryId, int start) {
	    return productDao.selectProductsByCategoryId(categoryId, start, ProductService.LIMIT);
	  }

	  @Override
	  @Transactional(readOnly = true)
	  public int getProductCountAll() {
	    return productDao.selectCountAll();
	  }

	  @Override
	  @Transactional(readOnly = true)
	  public int getProductCountByCategoryId(int categoryId) {
	    return productDao.selectCountByCategoryId(categoryId);
	  }

	  @Override
	  @Transactional(readOnly = true)
	  public List<ProductImage> getImagesByDisplayInfoId(int displayInfoId) {
	    return productImageDao.selectByDisplayInfoId(displayInfoId);
	  }

	  @Override
	  @Transactional(readOnly = true)
	  public List<ProductPrice> getPricesByDisplayInfoId(int displayInfoId) {
	    return productPriceDao.selectByDisplayInfoId(displayInfoId);
	  }

	  @Override
	  @Transactional(readOnly = true)
	  public FileInfo getProductFileInfoByProductImgId(int productImageId) {
	    return fileInfoDao.selectByProductImageId(productImageId);
	  }
	
	
}
