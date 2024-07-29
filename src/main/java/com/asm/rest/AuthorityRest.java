package com.asm.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asm.entity.Account;
import com.asm.entity.Authority;
import com.asm.entity.Role;
import com.asm.service.AccountService;
import com.asm.service.AuthorityService;
import com.asm.service.RoleService;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/authorities")
public class AuthorityRest {

	@Autowired
	AuthorityService authorityService;

	@GetMapping()
	public List<Authority> findAll(@RequestParam("admin") Optional<Boolean> admin) {
		if(admin.orElse(false)) {
			return authorityService.findAuthoritiesOfAdministrators();
		}
		return authorityService.findAll();
	}
	
	@PostMapping()
	public Authority post(@RequestBody Authority authority) {
		return authorityService.create(authority);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		authorityService.detele(id);
	}

}
