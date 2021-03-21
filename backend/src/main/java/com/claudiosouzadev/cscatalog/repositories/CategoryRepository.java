package com.claudiosouzadev.cscatalog.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claudiosouzadev.cscatalog.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	
}
