package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sns.comment.bo.CommentBO;
import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;
import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.domain.CardDTO;

import lombok.RequiredArgsConstructor;

/*
DB연동 : View영역 <--> Controller영역(Domain) <--> Service(BO)영역 <--> Repository영역(Mapper) <--> DB영역 
*/

//View영역

/*
<Response 방법 - 서버 기준>
@Controller + return String => ViewResolver => HTML 파일 렌더링(Model) => HTML
@Controller + @ResponseBody return String => HTTPMessageConverter => HTML
@Controller + @ResponseBody return 객체(map, list) => HTTPMessageConverter => jackson => JSON
@RestController return map => JSON
Model은 HTML일 경우 사용(@ResponseBody일 경우 Model 사용 불가)
*/

@RequiredArgsConstructor // 의존성 주입
@Controller
public class TimelineController {

	// 어노테이션(Annotation) - DI(Dependency Injection) : 의존성 주입
//	private final PostBO postBO;
//	private final CommentBO commentBO;
	private final TimelineBO timelineBO;
	
	@GetMapping("/timeline")
	// http:localhost/timeline
	public String timeline(Model model) {
		
		// DB SELECT - breakpoint
		//List<PostEntity> postList = postBO.getPostList();
		List<CardDTO> cardList = timelineBO.generateCardList();
		
		// MODEL 데이터 삽입 - breakpoint
		// Controller가 Model에 데이터를 삽입
		// HTML(VIEW)가 Model에서 꺼내서 사용
		// model.addAttribute("postList", postList);
		model.addAttribute("cardList", cardList);
		
		return "timeline/timeline";
	}
}
