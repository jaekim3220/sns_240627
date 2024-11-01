package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sns")
public class UserRestController {

	@GetMapping("/is-duplicate-id")
	public Map<String, Object> isDuplicateId(
			@RequestParam("loginId") String loginId) { // HTML, JS에서 설정한 파라미터(변수)를 사용
		
		// 응답 값 breakpoint 1
		// {"code":200, "is_duplicate_id":true}
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("is_duplicate_id", false);
		
		return result;
	}
	
}
