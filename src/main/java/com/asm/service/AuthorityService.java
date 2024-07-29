package com.asm.service;

import java.util.List;

import com.asm.entity.Authority;

public interface AuthorityService {

	List<Authority> findAuthoritiesOfAdministrators();

	List<Authority> findAll();

	Authority create(Authority authority);

	void detele(Integer id);

}
