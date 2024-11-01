package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

@RestController
@RequestMapping("/sns")
public class UserRestController {

	// 어노테이션(Annotation)
	@Autowired // DI(Dependency Injection) : 의존성 주입
	private UserBO userBO;
	
	@GetMapping("/is-duplicate-id")
	public Map<String, Object> isDuplicateId(
			@RequestParam("loginId") String loginId) { // HTML, JS에서 설정한 파라미터(변수)를 사용
		
		// DB SELECT breakpoint 2
		UserEntity user = userBO.getUserEntityByLoginId(loginId);
		
		// 응답 값 breakpoint 1
		// 응답값 => Map => JSON String
		// {"code":200, "is_duplicate_id":true}
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("is_duplicate_id", false);
		
		return result;
	}
	
}
