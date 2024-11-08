package com.sns.comment.domain;

import com.sns.user.entity.UserEntity;

import lombok.Getter;
import lombok.Setter;

// 댓글 1개의 내용
// @Data
@Getter
@Setter
public class CommentDTO {

	// 댓글 1개
	private Comment comment; // Comment 전체 추출
	
	
	// 댓글쓴이 1개
	private UserEntity user;
	
}
