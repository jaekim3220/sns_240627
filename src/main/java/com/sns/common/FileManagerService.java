package com.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Component // Spring Bean 등록
@Slf4j
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
	
	
	// 업로드된 이미지를 컴퓨터(서버)에서 삭제 : 삭제할 이미지 위치(주소) 추출 및 삭제 기능 구현
	// input : imagePath
	// output : X
	// /post/delete
	public void deleteFile(String imagePath) {
		// `D:\JAVA\6_spring_project\sns\sns_workspace\images/` => 서버 저장 경로
		// `/images/bbbb_1731062120745/whooper-swans-8640045_1280.jpg` => DB 저장 경로
		// /images/가 중복
		
		// 삭제 대상의 파일 경로 설정(중복 제거)
		// Path path = Paths.get(FILE_UPLOAD_PATH + imagePath.replace("/images/", ""));
		Path path = Paths.get(FILE_UPLOAD_PATH + imagePath.replace("/images/", ""));
		
		
		// 삭제할 파일(이미지)가 있는 경우
		if(Files.exists(path)) {
			// 서버에서 이미지 파일 삭제
			try {
				Files.delete(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace(); // => logging으로 대체 (서비스 동작 상태, 장애 파악 & 알림)
				log.info("[FileManagerService 파일 삭제] imagePath:{}", imagePath);
				return;
			}
		}
		
		// 서버에서 폴더(디렉토리) 삭제
		path = path.getParent(); // 파일 저장 폴더 위치
		
		// 삭제할 폴더가 있는 경우
		if(Files.exists(path)) {
			// 서버에서 이미지 파일 삭제
			try {
				Files.delete(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// logging을 사용한 확인(서비스 동작 상태, 장애 파악 & 알림)
				log.info("[FileManagerService 파일 삭제] imagePath:{}", imagePath);
				return;
			}
		}
		
		
	}
	
	
}
