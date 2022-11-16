package com.demo.blogging.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.blogging.config.AppConstants;
import com.demo.blogging.payloads.ApiResponse;
import com.demo.blogging.payloads.PostDto;
import com.demo.blogging.payloads.PostResponse;
import com.demo.blogging.services.FileService;
import com.demo.blogging.services.PostService;

@RestController
@RequestMapping("blogging-app/")
public class PostController {
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private PostService postService;
	
	@Value("${project.image}")
	private String path;
//	to create post 
	@PostMapping(value="user/{id}/category/{categoryId}/posts",consumes = "application/json",produces = "application/json")
	public ResponseEntity<PostDto> createPost(
		   @Valid
		   @RequestBody PostDto postDto,
		   @PathVariable Integer id,
		   @PathVariable Integer categoryId
		   )
	{
		PostDto createPost = this.postService.createPost(postDto, id, categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
//	to get all post	
	@GetMapping(value = "/posts",produces = "application/json")
	public ResponseEntity<List<PostDto>> getAllPost(){
		List<PostDto> posts = this.postService.getListOfPosts();
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
//	to get all posts by pagination
	@GetMapping(value = "/posts/page",produces = "application/json")
	public ResponseEntity<PostResponse> getAllPostByPage(

			@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber, 
			@RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false)String sortDir
														 ){
		PostResponse postResponse = this.postService.getListOfPostsByPage(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
//	to get post by id
	@GetMapping(value = "posts/{postId}",produces = "application/json")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		PostDto post = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
	}
	
//	to get all post by category
	@GetMapping(value = "category/{categoryId}/posts",produces = "application/json")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
		List<PostDto> posts = this.postService.getPostsByCategoryId(categoryId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
//	to get all post by user
	@GetMapping(value = "user/{id}/posts",produces = "application/json")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer id){
		List<PostDto> posts = this.postService.getPostsByUser(id);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
//	to update post
	@PutMapping(value = "/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@PathVariable Integer postId, @Valid @RequestBody PostDto postDto){
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}

//	to delete post
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("post deleted successfully.",true),HttpStatus.OK);
	}
	
//	to search posts be keyword
	@GetMapping(value = "/posts/search/{keywords}",produces = "application/json")
	public ResponseEntity<List<PostDto>> searchPostByPostTitle(@PathVariable("keywords") String keywords){
		List<PostDto> posts = this.postService.searchByPostTitle(keywords);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
//	post image upload
	@PostMapping("/posts/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(@RequestParam("image") MultipartFile file,@PathVariable Integer postId) throws IOException{
		PostDto postDto = this.postService.getPostById(postId);
		String fileName = this.fileService.uploadImage(path, file);
		postDto.setPostImageName(fileName);
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
		
	}

// method to serve files
	
	@GetMapping(value="posts/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImages(@PathVariable ("imageName") String imageName, HttpServletResponse response) throws IOException{
		
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}

}
