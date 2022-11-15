package com.community.numble.app.board.repository;

import com.community.numble.app.board.domain.Post;
import com.community.numble.app.board.domain.UploadFile;
import java.util.List;
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

	public void deletePostFile(Post post){
			em.createQuery("delete from UploadFile u where u.post = :post")
			.setParameter("post",post).executeUpdate();
	}

	public List<UploadFile> findByPost(Long post) {
		return em.createQuery("select u from UploadFile u where u.post = :post",UploadFile.class)
			.setParameter("post",post).getResultList();
	}
}
