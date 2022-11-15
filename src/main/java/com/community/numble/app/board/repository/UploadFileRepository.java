package com.community.numble.app.board.repository;

import com.community.numble.app.board.domain.UploadFile;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UploadFileRepository {

	private final EntityManager em;

	public void save(UploadFile uploadFile){
		em.persist(uploadFile);
	}
}
