package com.duke.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duke.booking.dao.FileInfoDao;
import com.duke.booking.dao.PromotionDao;
import com.duke.booking.dto.FileInfo;
import com.duke.booking.dto.Promotion;

@Service
public class DefaultPromotionService implements PromotionService {
	private final PromotionDao promotionDao;
	private final FileInfoDao fileInfoDao;
	
	@Autowired
	public DefaultPromotionService(PromotionDao promotionDao, FileInfoDao fileInfoDao) {
		
		this.promotionDao = promotionDao;
		this.fileInfoDao = fileInfoDao;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Promotion> getPromotions() {
	  return promotionDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public FileInfo getPromotionFileInfo(int productImageId) {
	  return fileInfoDao.selectByProductImageId(productImageId);
	}
	
	

}
