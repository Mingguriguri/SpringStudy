package com.cos.security1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.security1.model.User;

// JpaRepository는 기본적으로 CRUD 함수를 들고 있음.
// @Repository라는 어노테이션이 없어도 IoC가 됨. WHY? JpaRepository를 상속했기 때문
public interface UserRepository extends JpaRepository<User, Integer> {

	// Jpa Naming 전략
	// 'findBy'까지는 규칙 | 이 뒤부터 (Username)는 문법
	// SELECT * FROM user WHERE username = 1?
	public User findByUsername(String username); // Jpa Query Method

	// SELECT * FROM user WHERE provider = ?1 and providerId = ?2
	public Optional<User> findByProviderAndProviderId(String provider, String providerId);
}
