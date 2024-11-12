package com.sns.like.bo;

import org.apache.ibatis.annotations.Param;
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
		if (likeMapper.selectLikeCountByPostIdOrUserId(postId, userId) > 0) {
			// like 존재 => 삭제
			likeMapper.deleteLikeByPostIdUserId(postId, userId);
		} else {
			// like 부재
			likeMapper.insertLike(postId, userId);
		}
		
	}
	
	
	// 글(postId)에 해당하는 좋아요 개수
	// input : 글 번호(postId)
	// output : 좋아요 개수
	public int getLikeCountByPostId(int postId) {
		return likeMapper.selectLikeCountByPostIdOrUserId(postId, null);
	}
	
	
	// 좋아요 이미지 설정
	// input : postId(글 번호), userId(로그인된 사람)
	// 로그인 유저 또는 비로그인 유저(userId)
	// postEntity는 글쓴이의 데이터
	// 로그인된 유저는 TimelineController에서만 꺼낼 수 있음
	// output : boolean(채운다 : true, 비운다 : false)
	public boolean filledLikeByPostIdUserId(int postId, Integer userId) {
		// 1. 비로그인 => 빈 하트
		if (userId == null) {
			return false;
		}
		int likeCount = likeMapper.selectLikeCountByPostIdOrUserId(postId, userId);

		// 2. 로그인 => 누른적 없으면 빈 하트
		// 3. 로그인 => 누른적 있으면 채워진 하트
		return likeCount > 0; 
	}
	
}
