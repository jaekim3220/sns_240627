package com.sns.like.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/*
DB연동 : View영역 <--> Controller영역(Domain) <--> Service(BO)영역 <--> Repository영역(Mapper) <--> DB영역 
*/

// Controller영역(Domain)
@Getter
@Setter
public class Like {

	// 속성 : field
	private int postId;
	private int userId;
	private LocalDateTime createdAt;
	
}
