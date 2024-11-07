package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

/*
DB연동 : View영역 <--> Controller영역(Domain) <--> Service(BO)영역 <--> Repository영역(Mapper) <--> DB영역 
*/

// View영역

/*
<Response 방법 - 서버 기준>
@Controller + return String => ViewResolver => HTML 파일 렌더링(Model) => HTML
@Controller + @ResponseBody return String => HTTPMessageConverter => HTML
@Controller + @ResponseBody return 객체(map, list) => HTTPMessageConverter => jackson => JSON
@RestController return map => JSON
*/

@RestController
@RequestMapping("/comment")
public class CommentRestController {

	
	// 댓글 쓰기 API
	@PostMapping("/create")
	// http:localhost/comment/create
	public Map<String, Object> create(
			// 필수 파라미터 불러오기1 : value, required 생략 (추천) - null이 아닌 column - lesson03 참고
			@RequestParam("postId") int postId,
			@RequestParam("content") int content,
			HttpSession session) {
		
		// 로그인 여부 - breakpoint
		Integer userId = (Integer)session.getAttribute("userId");
		Map<String, Object> result = new HashMap<>();
		if (userId == null) {
			result.put("code", 403);
			result.put("error_message", "로그인을 해주세요.");
			return result;
		}
		
		
		// DB INSERT - breakpoint
		commentBO.addComment
		
		
		// Response(응답값) - breakpoint
		result.put("code", 200);
		result.put("result", "성공");
		return result;
		
	}
	
	
}
