package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

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

@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentRestController {

	
	/*
	// 어노테이션(Annotation)
	@Autowired // DI(Dependency Injection) : 의존성 주입
	private CommentBO commentBO;
	*/
	
	/* 생성자를 통한 의존성 주입(트렌드, 권장) */
//	private final CommentBO commentBO;
//	public CommentRestController(CommentBO commentBO) {
//		this.commentBO = commentBO;
//	}
	
	/* 파라미터가 너무 많을 경우 Lombok을 사용해 구현 가능 */
	private final CommentBO commentBO;
	
	
	/**
	 * 댓글쓰기 API
	 * @param postId
	 * @param content
	 * @param session
	 * @return
	 */
	@PostMapping("/create")
	// http:localhost/comment/create?postId=1&content=댓글테스트
	// http:localhost/comment/create
	public Map<String, Object> create(
			// 필수 파라미터 불러오기1 : value, required 생략 (추천) - null이 아닌 column - lesson03 참고
			@RequestParam("postId") int postId,
			@RequestParam("content") String content,
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
		commentBO.addComment(postId, userId, content);
		
		
		// Response(응답값) - breakpoint
		result.put("code", 200);
		result.put("result", "성공");
		return result;
		
	}
	
	
	/**
	 * 댓글 삭제 API
	 * @param commentId
	 * @param session
	 * @return
	 */
	@DeleteMapping("/delete")
	public Map<String, Object> delete(
			// 필수 파라미터 불러오기1 : value, required 생략 (추천) - null이 아닌 column - lesson03 참고
			@RequestParam("commentId") int commentId,
			HttpSession session) {
		
		// 로그인 여부 - breakpoint
		Map<String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("code", 403);
			result.put("error_message", "로그인이 되지 않은 사용자 입니다.");
			return result;
		}
		
		
		// DB DELETE - breakpoint
		commentBO.deleteCommentById(commentId);
		
		
		// Response(응답값) - breakpoint
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
	
}
