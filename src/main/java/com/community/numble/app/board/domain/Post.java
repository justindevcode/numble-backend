package com.community.numble.app.board.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter @Setter
public class Post extends Time{

	@Id @GeneratedValue
	@Column(name = "post_id")
	private Long id;

	private String title;
	private String content;

	private String location;

	@Enumerated(EnumType.STRING)
	private PostCategory type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@OneToMany(mappedBy = "post")
	private List<Comment> comment = new ArrayList<>();

	@OneToMany(mappedBy = "post")
	private List<PostLike> postlikes = new ArrayList<>();


	public void setMember(Member member){
		this.member = new Member();
		member.getPosts().add(this);
	}
}
