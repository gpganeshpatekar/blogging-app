package com.demo.blogging.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demo.blogging.config.AppConstants;
import com.demo.blogging.entities.Category;
import com.demo.blogging.entities.Post;
import com.demo.blogging.entities.User;
import com.demo.blogging.exceptions.ResourceNotFoundException;
import com.demo.blogging.payloads.PostDto;
import com.demo.blogging.payloads.PostResponse;
import com.demo.blogging.repositories.CategoryRepository;
import com.demo.blogging.repositories.PostRepository;
import com.demo.blogging.repositories.UserRepository;
import com.demo.blogging.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CategoryRepository categoryRepository;

//	to create post
	@Override
	public PostDto createPost(PostDto postDto, String username, Integer categoryId) {
		User user = this.userRepository.findByEmail(username)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstants.USER_NOT_FOUND+username));
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setPostImageName("default.png");
		post.setUser(user);
		post.setCategory(category);
		Post savePost = this.postRepository.save(post);
		return this.modelMapper.map(savePost, PostDto.class);
	}

//	to get all post
	@Override
	public List<PostDto> getListOfPosts() {
		List<Post> posts = this.postRepository.findAll();
		List<PostDto> postsDto = posts.stream().map(p -> this.modelMapper.map(p, PostDto.class))
				.collect(Collectors.toList());
		return postsDto;
	}

//	to get all posts by pagination
	@Override
	public PostResponse getListOfPostsByPage(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		Sort sort = (sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);
		Page<Post> pagePost = this.postRepository.findAll(pageable);
		List<Post> posts = pagePost.getContent();
		List<PostDto> postsDto = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postsDto);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPage(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
		
	}

//	to get post by id
	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

//	to get all post by category
	@Override
	public List<PostDto> getPostsByCategoryId(Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		List<Post> posts = this.postRepository.findByCategory(category);
		List<PostDto> postsDto = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postsDto;
	}

//	to get all post by user
	@Override
	public List<PostDto> getPostsByUser(Integer id) {
		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		List<Post> posts = this.postRepository.findByUser(user);
		List<PostDto> postsDto = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postsDto;
	}

//	to search posts be keyword
	@Override
	public List<PostDto> searchByPostTitle(String keywords) {
		List<Post> postList = this.postRepository.findByPostTitleContaining("%"+keywords+"%");
		List<PostDto> posts = postList.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		if(posts.isEmpty()) {
			throw new ResourceNotFoundException("post not found with search keywords : "+keywords);
		}
		return posts;
	}

//	to update post
	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		post.setPostTitle(postDto.getPostTitle());
		post.setPostContent(postDto.getPostContent());
		post.setPostImageName(postDto.getPostImageName());
		Post updatePost = this.postRepository.save(post);
		return this.modelMapper.map(updatePost, PostDto.class);
	}

//	to delete post
	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		this.postRepository.delete(post);

	}

}
