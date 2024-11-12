package com.sns.like.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sns.like.domain.Like;

/*
DB연동 : View영역 <--> Controller영역(Domain) <--> Service(BO)영역 <--> Repository영역(Mapper) <--> DB영역 
*/

// Repository영역(Mapper) - Mybatis 방식

@Mapper
public interface LikeMapper {

	
	// input : postId, userId
	// output : int
	// @GetMapping("/like/{postId}")
	public int selectLikeCountByPostIdUserId(
			// 다수의 파라미터일 경우 @Param 설정
			@Param("postId") int postId, 
			@Param("userId") int userId);
	
	
	// input : postId, userId
	// output : X
	// return(outPut) : 1 존재(like 존재), 0 부재(like 없음)
	// @GetMapping("/like/{postId}")
	public void insertLike(
			// 다수의 파라미터일 경우 @Param 설정
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	
	// input : postId, userId
	// output : Like
	// @GetMapping("/like/{postId}")
	public int deleteLikeByPostIdUserId(
			// 다수의 파라미터일 경우 @Param 설정
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	
	// input : postId
	// output : 좋아요 개수(int)
	public int selectLikeCountByPostId(int postId);
	
}
