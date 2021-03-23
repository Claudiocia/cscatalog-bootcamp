package com.claudiosouzadev.cscatalog.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.claudiosouzadev.cscatalog.entities.Category;
import com.claudiosouzadev.cscatalog.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;

	@Transactional(readOnly = true)
	public List<Category> findAll(){
		 
		return repository.findAll();
	}
	
}
