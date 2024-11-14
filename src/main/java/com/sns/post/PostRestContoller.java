package com.sns.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.bo.PostBO;

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
@RequestMapping("/post")
public class PostRestContoller {
	
	@Autowired
	private PostBO postBO;
	
	
	// 글쓰기 API
	@PostMapping("/create")
	// http:localhost/post/create
	public Map<String, Object> create(
            // 필수 파라미터 불러오기1 : value, required 생략 (추천) - null이 아닌 column - lesson03 참고
            @RequestParam("file") MultipartFile file ,
			// 비필수 파라미터 불러오기2 : 기본값 설정 (추천) - lesson03 참고
            @RequestParam(value = "content", required = false) String content,
            HttpSession session) {
		
		// 글쓴이 번호 추출(session) - 비로그인일 경우 에러 발생 - breakpoint
		Integer userId = (Integer) session.getAttribute("userId");
		String userLoginId = (String)session.getAttribute("userLoginId");
		
		
		// DB INSERT - breakpoint
				
		
		// Response(응답값) - breakpoint
		Map<String, Object> result = new HashMap<>();
		// 로그인 여부 확인
		if(userId == null) {
			result.put("code", 403);
			result.put("error_message", "로그인 해주세요");
			return result;
		}
		
		// postBO
		postBO.addPost(userId, userLoginId, content, file);
		
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
	
	
	// 글 삭제 API
	@DeleteMapping("/delete")
	public Map<String, Object> delete(
			// 필수 파라미터 불러오기1 : value, required 생략 (추천) - null이 아닌 column - lesson03 참고
			@RequestParam("postId") int postId,
			HttpSession session) {

		// session => userId(DB), userLoginId(파일 업로드) - breakpoint
		// session에 담을 변수(parameter)가 기억나지 않을 경우
		// UserRestController 참고
		Integer userId = (Integer)session.getAttribute("userId"); // 비로그인 사용자는 게시글 삭제 불가
		Map<String, Object> result = new HashMap<>();
		if(userId == null) {
			result.put("code", 403);
			result.put("error_message", "로그인 후 사용이 가능합니다.");
		}
		
		
		// DB DELETE - breakpoint
		postBO.deletePostByPostId(postId);
		
		
		// response(응답값) - breakpoint
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
		
	}
	
}
