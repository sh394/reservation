package com.duke.booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.duke.booking.dto.Category;

@Service
public interface CategoryService {
	public List<Category> getCategories();

}
