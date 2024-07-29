package com.asm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asm.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

}
