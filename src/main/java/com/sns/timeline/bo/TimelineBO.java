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
			
			// 생성한 DTO를 사용해 DB 데이터를 받을 변수를 생성 - breakpoint
			CardDTO card = new CardDTO();
			
			// DTO 변수에 setter를 사용해 `post`의 글 정보 삽입 - breakpoint
			card.setPostEntity(postEntity);
			
			// DTO 변수에 `post`의 글쓴이 정보 삽입 - breakpoint
			// 1)
			// int userId = postEntity.getUserId(); // 글쓴이 번호
			// 2) - User 영역을 거친 후 userId 데이터를 가져올 수 있도록 UserBO에 새로운 메서드 생성
			card.setUserEntity(userBO.getUserEntityById(postEntity.getUserId()));
			
			
			// ★★★★★ list에 DTD 삽입 - breakpoint
			cardList.add(card);
			
		}
		
		return cardList;
	}
	
	
	
}
