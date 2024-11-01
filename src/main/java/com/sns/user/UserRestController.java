package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {

//	@GetMapping("/is-duplicate-id")
//	public Map<String, Object> isDuplicateId(
//			@RequestParam("loginId") String loginId) {
//		
//		// 응답 값 breakpoint 1
//		Map<String, Object> result = new HashMap<>();
//		// TODO : result 값 넣기
//		
//		return result;
//	}
}
