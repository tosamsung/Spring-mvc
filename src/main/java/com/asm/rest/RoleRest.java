package com.asm.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asm.entity.Role;
import com.asm.service.RoleService;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin("*")
@RestController
@RequestMapping("/rest/roles")
public class RoleRest {

	@Autowired
	RoleService roleService;
	
	@GetMapping()
	public List<Role> getAll() {
		return roleService.findAll();
	}
	
}
