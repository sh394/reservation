package com.duke.booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duke.booking.dao.DisplayInfoDao;
import com.duke.booking.dao.DisplayInfoImageDao;
import com.duke.booking.dao.FileInfoDao;
import com.duke.booking.dto.DisplayInfo;
import com.duke.booking.dto.DisplayInfoImage;
import com.duke.booking.dto.FileInfo;

@Service
public class DefaultDisplayInfoService implements DisplayInfoService {
	private final DisplayInfoDao displayInfoDao;
	private final DisplayInfoImageDao displayInfoImageDao;
	private final FileInfoDao fileInfoDao;
	
	@Autowired
	public DefaultDisplayInfoService(DisplayInfoDao displayInfoDao, DisplayInfoImageDao displayInfoImageDao, FileInfoDao fileInfoDao) {
		this.displayInfoDao = displayInfoDao;
		this.displayInfoImageDao = displayInfoImageDao;
		this.fileInfoDao = fileInfoDao;
	}
	
	  @Override
	  @Transactional(readOnly = true)
	  public DisplayInfo getDisplayInfoById(int displayInfoId) {
	    return displayInfoDao.selectById(displayInfoId);
	  }

	  @Override
	  @Transactional(readOnly = true)
	  public DisplayInfoImage getImagesByDisplayInfoId(int displayInfoId) {
	    return displayInfoImageDao.selectByDisplayInfoId(displayInfoId);
	  }

	  @Override
	  @Transactional(readOnly = true)
	  public FileInfo getFileInfoByDisplayInfoImageId(int displayInfoImageId) {
	    return fileInfoDao.selectByDisplayInfoImageId(displayInfoImageId);
	  }

}
