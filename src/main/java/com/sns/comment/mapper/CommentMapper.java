package com.sns.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.sns.comment.domain.Comment;

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
	
	
	// DTO 생성
	public List<Comment> selectCommentList();
	public List<Comment> selectCommentListByPostId(int postId);
	
	// 댓글 삭제
	// @DeleteMapping("/delete")
	public int deleteCommentById(int commentId);
	
	
	// /post/delete
	public void deleteCommentListByPostId(int postId);
}
