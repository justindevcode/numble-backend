package com.community.numble.app.board.controller;

import com.community.numble.app.board.domain.Post;
import com.community.numble.app.board.domain.UploadFile;
import com.community.numble.app.board.dto.PostDto.CreatePostPhotoRequest;
import com.community.numble.app.board.dto.PostDto.CreatePostPhotoResponse;
import com.community.numble.app.board.dto.PostDto.CreatePostRequest;
import com.community.numble.app.board.dto.PostDto.CreatePostResponse;
import com.community.numble.app.board.dto.PostDto.PostAllDto;
import com.community.numble.app.board.dto.PostDto.PostResult;
import com.community.numble.app.board.repository.PostRepository;
import com.community.numble.app.board.service.MemberService;
import com.community.numble.app.board.service.PostService;
import com.community.numble.app.board.service.UploadFileService;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1") //버전명시(이전버전도 사용 분리)
public class PostApiController {

	private final PostService postService;
	private final MemberService memberService;
	private final UploadFileService uploadFileService;


	@PostMapping("/numble11/post")
	public ResponseEntity savePost(@RequestBody CreatePostRequest request) {
		Post post = new Post();
		post.setTitle(request.getTitle());
		post.setContent(request.getContent());
		post.setLocation(request.getLocation());
		post.createType(request.getType());
		post.setMember(memberService.findOne(request.getMember()));
		Long id = postService.post(post);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new CreatePostResponse(id));
	}

	@GetMapping("/numble11/post")
	public ResponseEntity getAllPost(){
		List<Post> findPosts = postService.findPost();
		List<PostAllDto> collect = findPosts.stream()
			.map(m ->new PostAllDto(m.getId(),m.getTitle(),m.getContent(),m.getLocation(),m.getType(),m.getMember().getId()))
			.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new PostResult(collect.size(),collect));
	}

	@PostMapping("/numble11/post/photo")
	public ResponseEntity savePostPhoto(@RequestPart(value = "file",required = false) MultipartFile[] imageFiles,
		@RequestPart(value = "postinfo") CreatePostPhotoRequest request) {
//업로드기능 service로 분리 DTO service로
		String uploadFolder = "C:\\Users\\aa\\Desktop\\numble11\\backend\\src\\main\\resources\\media\\postUplode";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		String datePath = str.replace("-", File.separator);
		File uploadPath = new File(uploadFolder, datePath);
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		Post post = new Post();
		post.setTitle(request.getTitle());
		post.setContent(request.getContent());
		post.setLocation(request.getLocation());
		post.createType(request.getType());
		post.setMember(memberService.findOne(request.getMember()));

		Long id = postService.post(post);

		if(imageFiles != null) {
			for (MultipartFile multipartFile : imageFiles) {
				String uploadFileName = multipartFile.getOriginalFilename();
				UploadFile uploadFile = new UploadFile();
				uploadFile.setImageFiles(uploadFileName);
				uploadFile.setPost(post);


				/* 파일 위치, 파일 이름을 합친 File 객체 */
				File saveFile = new File(uploadPath, uploadFileName);

				/* 파일 저장 */
				try {
					multipartFile.transferTo(saveFile);
					Long idFile = uploadFileService.upload(uploadFile);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}





		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new CreatePostPhotoResponse(id));
	}

	//파일명 파일경로:타입,날짜별 파일타입 저장되는다른파일이름:UUID바뀐거도 저장 , 확장자 ,파일사이즈,생성날자,수정날짜

	@DeleteMapping("/numble11/post/{id}")
	public ResponseEntity deletePost(@PathVariable("id") Long id){
		postService.deletePost(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("delete");

	}

	@PutMapping("/numble11/post/{id}")
	public ResponseEntity updatePost(@PathVariable("id") Long currentid,@RequestPart(value = "file",required = false) MultipartFile[] imageFiles,
		@RequestPart(value = "postinfo") CreatePostPhotoRequest request){

		String uploadFolder = "C:\\Users\\aa\\Desktop\\numble11\\backend\\src\\main\\resources\\media\\postUplode";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		String datePath = str.replace("-", File.separator);
		File uploadPath = new File(uploadFolder, datePath);
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		Post post = postService.findOne(currentid);
		uploadFileService.deletefile(post);
		post.setTitle(request.getTitle());
		post.setContent(request.getContent());
		post.setLocation(request.getLocation());
		post.createType(request.getType());
		post.setMember(memberService.findOne(request.getMember()));

		Long id = postService.post(post);



		if(!imageFiles[0].isEmpty()) {
			System.out.println(imageFiles);
			for (MultipartFile multipartFile : imageFiles) {
				String uploadFileName = multipartFile.getOriginalFilename();
				UploadFile uploadFile = new UploadFile();
				uploadFile.setImageFiles(uploadFileName);
				uploadFile.setPost(post);


				/* 파일 위치, 파일 이름을 합친 File 객체 */
				File saveFile = new File(uploadPath, uploadFileName);

				/* 파일 저장 */
				try {
					multipartFile.transferTo(saveFile);
					Long idFile = uploadFileService.upload(uploadFile);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}





		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new CreatePostResponse(id));

	}


}

