package com.sns.comment.bo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.mapper.CommentMapper;

/*
DB연동 : View영역 <--> Controller영역(Domain) <--> Service(BO)영역 <--> Repository영역(Mapper) <--> DB영역 
*/

// Service(BO)영역

@Service
public class CommentBO {

	@Autowired
	private CommentMapper commentMapper;
	
	public void insertComment(int postId, int userId, String content) {
		commentMapper.insertComment(postId, userId, content);
	}
	
}
