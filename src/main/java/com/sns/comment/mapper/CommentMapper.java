package com.sns.comment.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/*
DB연동 : View영역 <--> Controller영역(Domain) <--> Service(BO)영역 <--> Repository영역(Mapper) <--> DB영역 
*/

// Repository영역(Mapper) - Mybatis 방식

@Mapper
public interface CommentMapper {

	public void insertComment(
			@Param("postId") int postId,
			@Param("userId") int userId,
			@Param("content") String content);
	
	
}
