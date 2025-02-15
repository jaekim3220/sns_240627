package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.common.EncryptUtils;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
// Restcontroller 생성 이후 Entity, Repository, BO를 구현
public class UserRestController {

	// 어노테이션(Annotation)
	@Autowired // DI(Dependency Injection) : 의존성 주입
	private UserBO userBO;
	
	/**
	 * 아이디 중복확인
	 * @param loginId
	 * @return
	 */
	@GetMapping("/is-duplicate-id")
	public Map<String, Object> isDuplicateId(
			@RequestParam("loginId") String loginId) { // HTML, JS에서 설정한 파라미터(변수)를 사용
		
		// DB SELECT breakpoint 2 
		UserEntity user = userBO.getUserEntityByLoginId(loginId);
		
		// 중복인 경우/아닌 경우 구분 (Console 창의 SQL문에서 입력 값 return 확인)
		boolean isDuplicate = false; // false : 중복 아님
		if (user != null) { // 기존 값이 존재한다면 `중복`
			isDuplicate = true;
		}
		
		
		// 응답 값 breakpoint 1
		// 응답값 => Map => JSON String
		// {"code":200, "is_duplicate_id":true}
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("is_duplicate_id", false);
		
		return result;
	}
	
	
	/**
	 * 회원가입
	 * @param loginId
	 * @param password
	 * @param name
	 * @param email
	 * @return
	 */
	@PostMapping("/sign-up")
	public Map<String, Object> signUp(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("email") String email) {
		
		// md5 알고리즘 - hashing(복호화 불가로 암호화 아님) - 이건 예전에 뚫렸음
		// => 프로젝트에는 다른 암호화 알고리즘 사용
		// 아이디 : aaaa => 74b8733745420d4d33f80c4663dc5e5
		// 비밀번호 : aaaa => 74b8733745420d4d33f80c4663dc5e5
		// breakpoint 2
		String hashedPassword = EncryptUtils.md5(password);
		
		
		// DB INSERT
		UserEntity user = userBO.addUser(loginId, hashedPassword, name, email);
		
		// 응답 값 breakpoint 1
		// 응답값 => Map => JSON String
		// {"code":200, "is_duplicate_id":true}
		Map<String, Object> result = new HashMap<>();
		if(user != null) {
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("error_message", "회원가입에 실패했습니다.");
		}
		return result;
	}
	
	
	/**
	 * 로그인 API
	 * @param loginId
	 * @param password
	 * @param request
	 * @return
	 */
	@PostMapping("/sign-in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpServletRequest request) {
		
		// DB SELECT breakpoint 2(데이터가 있는 경우 : user, 없는 경우 : null)
		UserEntity user = userBO.getUserEntityByLoginIdPassword(loginId, password);
		
		// 응답값 breakpoint 1(Console 창에서 쿼리문 확인)
		Map<String, Object> result = new HashMap<>();
		if(user != null) {
			// session에 사용자 정보를 담는다(사용자 각각 담는다) => 로그인한 사용자 만큼 생성됨
			HttpSession session = request.getSession(); // session 생성
			session.setAttribute("userId", user.getId()); // session에 삽입
			session.setAttribute("userLoginId", user.getLoginId()); // session에 삽입
			session.setAttribute("userName", user.getName()); // session에 삽입
			
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 300);
			result.put("error_message", "존재하지 않는 사용자입니다.");
		}
		
		return result;
		
	}
	
	
}
