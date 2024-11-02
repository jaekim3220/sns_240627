package com.sns.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// 공통 class 저장소
// Spring Bean은 다른 Spring Bean을 참조할 때 설정
public class EncryptUtils {

	// hashing 기능 구현
		// input : 원본 비밀번호
		// output : hashing된 비밀번호
		public static String md5(String message) {
			String encData = "";
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] bytes = message.getBytes();
				md.update(bytes);
				byte[] digest = md.digest();
				for (int i = 0; i < digest.length; i++) {
					encData += Integer.toHexString(digest[i] & 0xff); // 16진수로 변환하는 과정
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			return encData;
		}
		
}
