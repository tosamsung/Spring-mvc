package com.asm.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asm.dao.CategoryRepository;
import com.asm.entity.Category;
import com.asm.entity.Product;
import com.asm.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/categories")
public class CategoryRest {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	
	
	@GetMapping()
	public List<Category> getAll() {
		return categoryRepository.findAll();
	}
	
}
