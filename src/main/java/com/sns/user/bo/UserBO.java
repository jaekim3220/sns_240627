package com.sns.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sns.user.entity.UserEntity;
import com.sns.user.repository.UserRepository;

@Service
public class UserBO {

	@Autowired
	private UserRepository userRepository;
	
	// 컨트롤러한테 (아이디 중복 확인 용)
	// input : loginId
    // output : UserEntity or null (단건일 경우 Optional로 감싸서 구현)
	// @GetMapping("/is-duplicate-id") 구현
	public UserEntity getUserEntityByLoginId(String loginId) {
		return userRepository.findByLoginId(loginId);
	}
	
	
	// 컨트롤러한테 (아이디 중복 확인 용)
	// input : 파라미터 4개
    // output : UserEntity or null (단건일 경우 Optional로 감싸서 구현)
	// @PostMapping("/sign-up") 구현
	public UserEntity addUser(
			String loginId, String password, 
			String name, String email) {
		
		return userRepository.save( // Parameter를 Repository에 저장
				UserEntity.builder()
				.loginId(loginId)
				.password(password)
				.name(name)
				.email(email)
				.build()); 
	}
	
	
}
