package com.claudiosouzadev.cscatalog.services;


import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.claudiosouzadev.cscatalog.dto.CategoryDTO;
import com.claudiosouzadev.cscatalog.dto.ProductDTO;
import com.claudiosouzadev.cscatalog.entities.Category;
import com.claudiosouzadev.cscatalog.entities.Product;
import com.claudiosouzadev.cscatalog.repositories.CategoryRepository;
import com.claudiosouzadev.cscatalog.repositories.ProductRepository;
import com.claudiosouzadev.cscatalog.services.exceptions.DatabaseException;
import com.claudiosouzadev.cscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(PageRequest pageRequest){
		 Page<Product> list = repository.findAll(pageRequest);
		 
		 return list.map(x -> new ProductDTO(x));
	}

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> opt = repository.findById(id);
		Product entity = opt.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
		
		return new ProductDTO(entity, entity.getCategories()); 
	}

	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		
		return new ProductDTO(entity);
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new ProductDTO(entity);
		}catch(EntityNotFoundException e){
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}

	}
	
	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setDate(dto.getDate());
		entity.setImgUrl(dto.getImgUrl());
		entity.setPrice(dto.getPrice());
		
		entity.getCategories().clear();
		for (CategoryDTO catDto : dto.getCategories()) {
			Category category = categoryRepository.getOne(catDto.getId());
			entity.getCategories().add(category);
		}
	}
	
	
	
}
