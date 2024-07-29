package com.asm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.dao.AccountRepository;
import com.asm.dao.AuthorityRepository;
import com.asm.entity.Account;
import com.asm.entity.Authority;
import com.asm.service.AccountService;
import com.asm.service.AuthorityService;

@Service
public class AuthorityServiceIpml implements AuthorityService {

	@Autowired
	AuthorityRepository authorityRepository;
	
	@Autowired
	AccountService accountService;

	

	@Override
	public List<Authority> findAll() {
		// TODO Auto-generated method stub
		return authorityRepository.findAll();
	}



	@Override
	public List<Authority> findAuthoritiesOfAdministrators() {
		List<Account> accounts = accountService.getAdministrators();
		return authorityRepository.authoritiesOf(accounts);
	}



	@Override
	public Authority create(Authority authority) {
		// TODO Auto-generated method stub
		return authorityRepository.save(authority);
	}



	@Override
	public void detele(Integer id) {
		authorityRepository.deleteById(id);		
	}

}
