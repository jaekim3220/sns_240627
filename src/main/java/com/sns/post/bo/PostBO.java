package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileManagerService;
import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;

/*
DB연동 : View영역 <--> Controller영역(Domain) <--> Service(BO)영역 <--> Repository영역(Mapper) <--> DB영역 
*/

//Service(BO)영역

@Service // Spring Bean 등록
public class PostBO {

	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private FileManagerService fileManager;
	
	
	// input:X
	// output:List<PostEntity>
	public List<PostEntity> getPostList() {
		return postRepository.findByOrderByIdDesc(); // 최신 데이터 우선(orderBy)
	}
	
	
	// input : userId, content, file
	// output : PostEntity
	// @PostMapping("/create") 구현
	// 이미지 파라미터(이미지)업로드
	public PostEntity addPost(int userId, String userLoginId,
			String content, MultipartFile file) {
		
		// 파일이 있을 경우에만 업로드 => imagePath 추출 - breakpoint
		String imagePath = fileManager.uploadFile(file, userLoginId); // 파일이 있는 경우에만 파일을 넘긴다
			
		// return 0;
		return postRepository.save(
				PostEntity.builder()
				.userId(userId)
				.content(content)
				.imagePath(imagePath)
				.build());
	}
	
}
