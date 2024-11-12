package com.sns.timeline.domain;

import java.util.List;

import com.sns.comment.domain.CommentDTO;
import com.sns.post.entity.PostEntity;
import com.sns.user.entity.UserEntity;

import lombok.Getter;
import lombok.Setter;

// DTO는 `계층간 데이터 교환을 위해 사용하는 객체`로 데이터 교환이 일어나는 객체 만큼 생성
// 글 1개 => HTML의 card 1개

// @Data // @Data의 경우 가끔 에러가 발생할 수도 있으므로 Getter, Setter 사용
@Getter
@Setter
public class CardDTO {

	// 글 1개
	private PostEntity postEntity; // postEntity 전체 추출
	
	
	// 글쓴이
	private UserEntity userEntity;
	
	
	// 댓글 N개
	private List<CommentDTO> commentList;
	
	
	// 좋아요 N개
	private int likeCount;
	
	
	// 좋아요 여부
	private boolean filledLike;
	
}
