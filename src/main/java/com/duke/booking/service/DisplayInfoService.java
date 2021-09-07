package com.duke.booking.service;

import org.springframework.stereotype.Service;

import com.duke.booking.dto.DisplayInfo;
import com.duke.booking.dto.DisplayInfoImage;
import com.duke.booking.dto.FileInfo;

@Service
public interface DisplayInfoService {
	  public DisplayInfo getDisplayInfoById(int displayInfoId);

	  public DisplayInfoImage getImagesByDisplayInfoId(int displayInfoId);

	  public FileInfo getFileInfoByDisplayInfoImageId(int displayInfoImageId);

}
