package com.demo.blogging.services;

import java.util.List;

import com.demo.blogging.payloads.PostDto;
import com.demo.blogging.payloads.PostResponse;

public interface PostService {
	
// 		to create post 
		PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
//		to get all post
		List<PostDto> getListOfPosts();
//		to get all posts by pagination
		PostResponse getListOfPostsByPage(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);
//		to get post by id
		PostDto getPostById(Integer postId);
//		to get all post by category
		List<PostDto> getPostsByCategoryId(Integer categoryId);
//		to get all post by user
		List<PostDto> getPostsByUser(Integer userId);
//		to search posts be keyword
		List<PostDto> searchByTitle(String keyword);
//		to update post
		PostDto updatePost(PostDto postDto, Integer postId);
//		to delete post
		void deletePost(Integer postId);

}
