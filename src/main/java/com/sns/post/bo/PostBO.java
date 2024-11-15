package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.common.FileManagerService;
import com.sns.like.bo.LikeBO;
import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
DB연동 : View영역 <--> Controller영역(Domain) <--> Service(BO)영역 <--> Repository영역(Mapper) <--> DB영역 
*/

//Service(BO)영역

@Service // Spring Bean 등록
@Slf4j // slf4j logger
@RequiredArgsConstructor
public class PostBO {

	// log 사용법 1)
	// private Logger log = LoggerFactory.getLogger(PostBO.class);
	
	// log 사용법 2)
	// private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private FileManagerService fileManager;
	
	private final CommentBO commentBO;
	private final LikeBO likeBO;
	
	
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
	
	
	// input : postId
	// output : X
	// post/delete
	@Transactional // 삭제 실패시 자동 복구(RollBack을 위한 어노테이션) - springFrameWork
	public void deletePostByPostId(int postId) {

		// DB 행 삭제 전 이미지(파일) 경로 확인 - breakpoint
		// 로그인한 사용자가 선택한 postId(id)와 동일한 행만 추출
		PostEntity post =  postRepository.findById(postId).orElse(null);
	    if (post == null) {
	    	// 없는 것 처리
	    	// logging을 사용한 확인(서비스 동작 상태, 장애 파악 & 알림)
	    	log.info("[글 삭제] post is null. postId:{}", postId);
	    	return;
	    }
	    
	    log.info("[글 삭제] post is null. postId:{}", postId);
		
	    
		// DB DELETE - breakpoint
		// 로그인한 사용자가 선택한 postId(id)와 동일한 게시글(post) 삭제
	    // postRepository.delete(post); // JPA 형식(PostEntity의 postId를 인자로 받아 삭제를 진행
	    postRepository.deleteById(postId);
		// comment 테이블에서 postId(id)가 동일한 행 삭제
	    commentBO.deleteCommentListByPostId(postId);
		// like 테이블에서 postId(id)가 동일한 행 삭제
	    likeBO.deleteLikeListByPostId(postId);
		
		
		// 기존글에 존재하는 이미지(폴더/파일) 삭제 - breakpoint
	    // postEntity를 사용해 DB에 저장된 이미지 경로를 추출, FileManagerService의 메서드를 사용해 이미지 파일/폴더 제거
	    fileManager.deleteFile(post.getImagePath());
		
	}
	
}
