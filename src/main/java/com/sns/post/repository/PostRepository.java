package com.sns.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sns.post.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Integer>{

	// 메서드 생성
	
	// @GetMapping("/timeline") 메서드
	public List<PostEntity> findByOrderByIdDesc();
}
