package com.community.numble.app.board.service;

import com.community.numble.app.board.domain.Post;
import com.community.numble.app.board.domain.UploadFile;
import com.community.numble.app.board.repository.UploadFileRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UploadFileService {

	private final UploadFileRepository uploadFileRepository;

	public long upload(UploadFile uploadFile){
		uploadFileRepository.save(uploadFile);
		return uploadFile.getId();
	}

	public void deletefile(Post post){
		uploadFileRepository.deletePostFile(post);
	}

	public List<UploadFile> findFilePost(Long postId) {
		return uploadFileRepository.findByPost(postId);

	}

}
