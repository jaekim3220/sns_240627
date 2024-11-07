package com.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component // Spring Bean 등록
public class FileManagerService {

	// 실제 업로드 된 이미지가 저장될 서버 경로
	public static final String FILE_UPLOAD_PATH = "D:\\JAVA\\6_spring_project\\sns\\sns_workspace\\images/";
	
	
	// 메서드 생성
	// input : MultipartFile, userLoginId
	// output : String(이미지 경로)
	public String uploadFile(MultipartFile file, String loginId) {
		// 폴더(directory) 생성 - breakpoint
		// 예 : aaaa_17237482234/sun.png
		String directoryName = loginId + "_" + System.currentTimeMillis();
		String filePath = FILE_UPLOAD_PATH + directoryName + "/"; // D:\\JAVA\\6_spring_project\\sns\\sns_workspace\\images/aaaa_17237482234/
		
		// 폴더 생성 - breakpoint
		File directory = new File(filePath);
		if(directory.mkdir() == false) { // 폴더 생성 실패
			return null; // 폴더 생성 시 실패할 경우 경로를 null로 리턴(에러를 방지해 나머지 데이터가 DB에 들어가도록 설정)
		}
		
		
		// 파일 업로드
		try {
			byte[] bytes = file.getBytes();
			// ★★★★★ 한글이 들어간 이미지는 업로드 불가 => 영문자 이미지 사용 ★★★★★
			Path path = Paths.get(filePath + file.getOriginalFilename()); // 어느 경로에 어느 이름으로 넣을 것인지 지정
			Files.write(path, bytes); // 설정한 path에 bytes 데이터(파일) 추가
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null; // 이미지 업로드 시 실패하면 결로를 null로 return해 DB INSERT를 정상적으로 진행
		}
		
		
		// 파일 업로드에 성공하면 이미지 url path 리턴(폴더 생성 + 폴더에 이미지 업로드)
		// => 주소는 위와 같이 될 것이라고 설정(예언)
		// => `/images/aaaa_17237482234/sun.png`
		return "/images/" + directoryName + "/" + file.getOriginalFilename(); // 여기로 오면 성공
	}
	
}
