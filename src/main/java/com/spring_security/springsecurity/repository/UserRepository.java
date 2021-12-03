package com.spring_security.springsecurity.repository;

import com.spring_security.springsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    public User findByUsername(String username);

    public List<User> findAllByRoleId(int role_id);
}
