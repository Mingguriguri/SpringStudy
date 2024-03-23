package com.cos.security1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.security1.model.User;

// JpaRepository는 기본적으로 CRUD 함수를 들고 있음.
// @Repository라는 어노테이션이 없어도 IoC가 됨. WHY? JpaRepository를 상속했기 때문
public interface UserRepository extends JpaRepository<User, Integer> {

}
