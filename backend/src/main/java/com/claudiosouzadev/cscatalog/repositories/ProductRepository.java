package com.claudiosouzadev.cscatalog.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claudiosouzadev.cscatalog.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	
}
