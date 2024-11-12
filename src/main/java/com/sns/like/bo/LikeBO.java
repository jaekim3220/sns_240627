package com.sns.like.bo;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.sns.like.mapper.LikeMapper;

import lombok.RequiredArgsConstructor;

/*
DB연동 : View영역 <--> Controller영역(Domain) <--> Service(BO)영역 <--> Repository영역(Mapper) <--> DB영역 
*/

// Service(BO)영역

@Service
@RequiredArgsConstructor
public class LikeBO {

	// 생성자를 통한 DI
	private final LikeMapper likeMapper;
	
	// toggle : 좋아요/해제
	// input : postId, userId
	// output : X
	// @GetMapping("/like/{postId}")
	public void toggleLike(int postId, int userId) {
		// 조회 - postId, userId => 있으면 삭제, 없으면 추가
		if (likeMapper.selectLikeCountByPostIdUserId(postId, userId) > 0) {
			// like 존재 => 삭제
			likeMapper.deleteLikeByPostIdUserId(postId, userId);
		} else {
			// like 부재
			likeMapper.insertLike(postId, userId);
		}
		
	}
	
	
}
