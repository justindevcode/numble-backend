package com.community.numble.app.board.repository;

import com.community.numble.app.board.domain.Member;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

	private final EntityManager em;

	public void save(Member member) {
		em.persist(member);
	}

	public Member findOne(Long id) {
		return em.find(Member.class, id);
	}

	public List<Member> findAll() {
		List<Member> result = em.createQuery("select m from Member m", Member.class)
			.getResultList();
		return result;
	}

	public List<Member> findByName(String name) {
		return em.createQuery("select m from Member m where m.name = :name", Member.class)
			.setParameter("name",name)
			.getResultList();
	}
}
