package com.sns.like;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sns.like.bo.LikeBO;
import com.sns.like.mapper.LikeMapper;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LikeRestController {

	// GET : /like?postId=3	// @RequestParam("postId")
	// Get : /like/3	/like/{postId} // wildCard 형식 @PathVariable(name="postId")
	
	private final LikeBO likeBO;
	
	// Like(좋아요) API
	@GetMapping("/like/{postId}")
	// http:localhost/like/
	public Map<String, Object> likeToggle(
			// 필수 파라미터 불러오기1 : value, required 생략 (추천) - null이 아닌 column - lesson03 참고
			@PathVariable(name="postId") int postId, // wildCard 문법
			HttpSession session) {
		
		// 로그인 여부 확인 - breakpoint
		// session에 담을 변수(parameter)가 기억나지 않을 경우
		// UserRestController 참고
		Integer userId = (Integer)session.getAttribute("userId");
		Map<String, Object> result = new HashMap<>();
		if (userId == null) { // 비로그인일 경우
			result.put("code", 403);
			result.put("error_message", "로그인을 해주세요.");
			return result;
		}
		
		
		// toggle 요청 => BO(BO에서 INSERT, DELETE 여부 결정) - breakpoint
		likeBO.toggleLike(postId, userId);
		
		
		// Response(응답값) - breakpoint
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
	
}