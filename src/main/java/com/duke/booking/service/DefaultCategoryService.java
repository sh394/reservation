package com.duke.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duke.booking.dao.CategoryDao;
import com.duke.booking.dto.Category;

@Service
public class DefaultCategoryService implements CategoryService{
	
	private CategoryDao categoryDao;
	
	@Autowired
	public DefaultCategoryService(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public List<Category> getCategories() {
		
		return categoryDao.selectAll();
	}

}
