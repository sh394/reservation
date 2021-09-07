package com.duke.booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.duke.booking.dto.FileInfo;
import com.duke.booking.dto.Promotion;

@Service
public interface PromotionService {
	public List<Promotion> getPromotions();

	public FileInfo getPromotionFileInfo(int productImageId);

}
