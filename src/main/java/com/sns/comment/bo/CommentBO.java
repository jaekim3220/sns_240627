package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.domain.Comment;
import com.sns.comment.domain.CommentDTO;
import com.sns.comment.mapper.CommentMapper;
import com.sns.user.bo.UserBO;

import lombok.RequiredArgsConstructor;

/*
DB연동 : View영역 <--> Controller영역(Domain) <--> Service(BO)영역 <--> Repository영역(Mapper) <--> DB영역 
*/

// Service(BO)영역

@Service
@RequiredArgsConstructor
public class CommentBO {

	@Autowired
	private CommentMapper commentMapper;
	private final UserBO userBO;
	
	public void addComment(int postId, int userId, String content) {
		commentMapper.insertComment(postId, userId, content);
	}
	
	
	// input : 글 번호
	// output : List<CommentDTO>
	// `글` 하나에 포함된 댓글 목록을 추출
	public List<CommentDTO> generateCommentList(int postId) {
		// `int postId`에 해당하는 comment 목록을 담을 List 변수 생성
		List<CommentDTO> commentDTOList = new ArrayList<>();
		
		
		// 게시글(card)에 해당하는 댓글 추출(List<Comment>) - breakpoint
		List<Comment> commentList = commentMapper.selectCommentListByPostId(postId);
		
		
		// 반복문 : Comment => CommentDTO => list에 삽입
		for(Comment comment : commentList) { // 향상된 for문
			// 생성한 DTO를 사용해 DB 데이터를 받을 변수를 생성 - breakpoint
			CommentDTO commentDTO = new CommentDTO();
			
			// DTO 변수에 setter를 사용해 `comment`의 글 정보 삽입 - breakpoint
			// 댓글 1개
			commentDTO.setComment(comment);
			
			// DTO 변수에 `comment`의 글쓴이 정보 삽입 - breakpoint
			// int userId = comment.getUserId(); // 글쓴이 번호
			commentDTO.setUser(userBO.getUserEntityById(comment.getUserId()));
			
			// ★★★★★ list에 DTD 삽입 - breakpoint
			commentDTOList.add(commentDTO);
		}
		
		return commentDTOList;
	}
	
}
