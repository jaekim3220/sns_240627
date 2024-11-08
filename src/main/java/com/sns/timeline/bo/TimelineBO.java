package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;
import com.sns.timeline.domain.CardDTO;
import com.sns.user.bo.UserBO;

import lombok.RequiredArgsConstructor;

/*
DB연동 : View영역 <--> Controller영역(Domain) <--> Service(BO)영역 <--> Repository영역(Mapper) <--> DB영역 
*/

@RequiredArgsConstructor // 의존성 주입
@Service
public class TimelineBO {
	
	
	// 어노테이션(Annotation) - DI(Dependency Injection) : 의존성 주입
	private final PostBO postBO;
	private final UserBO userBO;

	
	// input : X 
	// output : List<CardDTO>
	// HTML의 .card 목록(class)을 List로 만들고 post를 담는 각 .card를 List에 담는다.  
	public List<CardDTO> generateCardList() {
		List<CardDTO> cardList = new ArrayList<>();
		
		// 글 목록 추출(모든 목록을 추출 후 List 형식으로 저장) - breakpoint
		List<PostEntity> postList = postBO.getPostList();
		
		
		// 글 1개를 각각 CardDTO로 변환(반복문) - breakpoint
		for (PostEntity postEntity : postList) { // 향상된 for문
			
		}
		
		return cardList;
	}
	
	
	
}
