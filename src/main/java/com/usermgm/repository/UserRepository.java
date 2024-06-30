package com.usermgm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usermgm.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
