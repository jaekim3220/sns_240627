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
	// output : Like
	public Like selectLikeByPostIdUserId();
	
	
	// input : postId, userId
	// output : X
	public void insertComment(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	
	// input : postId, userId
	// output : Like
	public int deleteLikeByPostIdUserId();
	
}
