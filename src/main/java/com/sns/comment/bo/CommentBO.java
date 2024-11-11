package com.sns.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.domain.Comment;
import com.sns.comment.domain.CommentDTO;
import com.sns.comment.mapper.CommentMapper;

/*
DB연동 : View영역 <--> Controller영역(Domain) <--> Service(BO)영역 <--> Repository영역(Mapper) <--> DB영역 
*/

// Service(BO)영역

@Service
public class CommentBO {

	@Autowired
	private CommentMapper commentMapper;
	
	public void addComment(int postId, int userId, String content) {
		commentMapper.insertComment(postId, userId, content);
	}
	
	
	// input : 글 번호
	// output : List<CommentDTO>
	// `글` 하나에 포함된 댓글 목록을 추출
	public List<CommentDTO> generateCommentList(int postId) {
		// `int postId`에 해당하는 comment 목록을 담을 List 변수 생성
		
		
		// 게시글(card)에 해당하는 댓글 추출(List<Comment>) - breakpoint
		
		
		// 반복문 : Comment => CommentDTO => list에 삽입
		for(Comment comment : commentList) { // 향상된 for문
			// 생성한 DTO를 사용해 DB 데이터를 받을 변수를 생성 - breakpoint
			
			// DTO 변수에 setter를 사용해 `comment`의 글 정보 삽입 - breakpoint
			
			// DTO 변수에 `comment`의 글쓴이 정보 삽입 - breakpoint
			
		}
		
		return commentDTOList;
	}
	
}
