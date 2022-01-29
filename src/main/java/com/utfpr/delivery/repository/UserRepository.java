package com.utfpr.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.utfpr.delivery.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByUuid(String uuid);
	
	@Query("select usu from User usu where usu.email like :email")
	public User selectByEmail(@Param("email") String email);
}
