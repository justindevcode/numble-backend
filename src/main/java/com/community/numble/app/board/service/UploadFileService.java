package com.community.numble.app.board.service;

import com.community.numble.app.board.domain.UploadFile;
import com.community.numble.app.board.repository.UploadFileRepository;
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

}
