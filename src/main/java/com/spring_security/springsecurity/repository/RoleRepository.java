package com.spring_security.springsecurity.repository;

import com.spring_security.springsecurity.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

    public Role findByRoleName(String role_name);
}
