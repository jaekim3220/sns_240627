package com.sns.user.bo;

import org.springframework.beans.factory.annotation.Autowired;

import com.sns.user.entity.UserEntity;
import com.sns.user.repository.UserRepository;

public class UserBO {

	@Autowired
	private UserRepository userRepository;
	
	// 컨트롤러한테 (아이디 중복 확인 용)
	// input : loginId
    // output : UserEntity or null (단건)
	public UserEntity getUserEntityByLoginId(String loginId) {
		return userRepository.findByLoginId(loginId);
	}
}
