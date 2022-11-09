package com.community.numble.app.board.service;

import com.community.numble.app.board.domain.Comment;
import com.community.numble.app.board.domain.Post;
import com.community.numble.app.board.repository.CommentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;

	public Long save(Comment comment){
		commentRepository.save(comment);
		return comment.getId();
	}

	public List<Comment> findComment() {
		return commentRepository.findAll();
	}

	public Comment findOne(Long commentId) {
		return commentRepository.findOne(commentId);
	}


	public List<Comment> findCommentMember(Long memberid) {
		return commentRepository.findByMember(memberid);

	}

	public List<Comment> findCommentPost(Long postid) {
		return commentRepository.findByPost(postid);

	}

}
