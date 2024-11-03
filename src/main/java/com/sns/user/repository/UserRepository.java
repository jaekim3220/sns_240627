package com.sns.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sns.user.entity.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	// 메서드 생성(loginId를 사용한 DB 값 return)
    public UserEntity findByLoginId(String loginId);// 단건은 Optional. List는 Optional 불필요
    
	// @PostMapping("/sign-in") 메서드 생성
	public UserEntity findByLoginIdAndPassword(String loginId, String password);
}
