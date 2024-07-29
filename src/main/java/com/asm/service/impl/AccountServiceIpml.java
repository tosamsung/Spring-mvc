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
public class AccountServiceIpml implements AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Override
	public Account findById(String username) {

		return accountRepository.findById(username).get();
	}

	@Override
	public List<Account> getAdministrators() {
		// TODO Auto-generated method stub
		return accountRepository.getAdministrators();
	}

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return accountRepository.findAll();
	}

}
