package com.community.numble.app.board.controller;

import com.community.numble.app.board.domain.Post;
import com.community.numble.app.board.domain.PostCategory;
import com.community.numble.app.board.dto.PostDto.CreatePostPhotoRequest;
import com.community.numble.app.board.dto.PostDto.CreatePostPhotoResponse;
import com.community.numble.app.board.dto.PostDto.PostAllDto;
import com.community.numble.app.board.dto.PostDto.PostOneDto;
import com.community.numble.app.board.dto.PostDto.PostResult;
import com.community.numble.app.board.service.MemberService;
import com.community.numble.app.board.service.PostService;
import com.community.numble.app.board.service.UploadFileService;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1") //버전명시(이전버전도 사용 분리)
public class PostApiController {

	private final PostService postService;
	private final MemberService memberService;
	private final UploadFileService uploadFileService;


	//카테고리 api


	@GetMapping("/numble11/post")
	public ResponseEntity getAllPost(){
		List<Post> findPosts = postService.findPost();
		List<PostAllDto> collect = findPosts.stream()
			.map(m ->new PostAllDto(m.getId(),m.getTitle(),m.getContent(),m.getLocation(),m.getType(),m.getMember().getId(),
				uploadFileService.findFirstImage(m.getId()),m.getCreateData(),m.getModifiedDate()))
			.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new PostResult(collect.size(),collect));
	}

	@GetMapping("/numble11/category")
	public ResponseEntity getAllcategory(){
		Map<String, Object> enums = new LinkedHashMap<>();
		Class categoryType = PostCategory.class;
		enums.put("category", Arrays.stream(categoryType.getEnumConstants()).map(m -> postService.createTypeTrn(m.toString())));
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(enums);
	}

	@GetMapping("/numble11/post/{id}")
	public ResponseEntity getPostId(@PathVariable("id") Long id){
		Post post = postService.findOne(id);
		PostOneDto postOneDto = new PostOneDto(post.getId(),post.getTitle(),post.getContent(),post.getLocation(),post.getType(),post.getMember().getId(),
			uploadFileService.findAllImage(post.getId()),post.getCreateData(),post.getModifiedDate());
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(postOneDto);

	}

	@PostMapping("/numble11/post")
	public ResponseEntity savePostPhoto(@RequestPart(value = "file",required = false) MultipartFile[] imageFiles,
		@RequestPart(value = "postinfo") CreatePostPhotoRequest request) {

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new CreatePostPhotoResponse(uploadFileService.savePostImage(imageFiles,request)));
	}

	//파일명 파일경로:타입,날짜별 파일타입 저장되는다x른파일이름:UUID바뀐거도 저장 , 확장자 ,파일사이즈,생성날자,수정날짜

	@DeleteMapping("/numble11/post/{id}")
	public ResponseEntity deletePost(@PathVariable("id") Long id){
		postService.deletePost(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("delete");

	}

	@PutMapping("/numble11/post/{id}")
	public ResponseEntity updatePost(@PathVariable("id") Long currentid,@RequestPart(value = "file",required = false) MultipartFile[] imageFiles,
		@RequestPart(value = "postinfo") CreatePostPhotoRequest request){


		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new CreatePostPhotoResponse(uploadFileService.savePostImage(imageFiles,request,currentid)));

	}


	@GetMapping(
		value = "/numble11/postimage/{id}",
		produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE}
	)
	public @ResponseBody byte[] getImageWithMediaType(@PathVariable("id") Long imageid) throws IOException {

		InputStream in = this.getClass().getResourceAsStream(uploadFileService.findFilePath(imageid).replace('\\', '/'));
		System.out.println(in);
		return IOUtils.toByteArray(in);
	}




//	@PostMapping("/numble11/post")
//	public ResponseEntity savePost(@RequestBody CreatePostRequest request) {
//		Post post = new Post();
//		post.setTitle(request.getTitle());
//		post.setContent(request.getContent());
//		post.setLocation(request.getLocation());
//		post.createType(request.getType());
//		post.setMember(memberService.findOne(request.getMember()));
//		Long id = postService.post(post);
//
//		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new CreatePostResponse(id));
//	}


}

