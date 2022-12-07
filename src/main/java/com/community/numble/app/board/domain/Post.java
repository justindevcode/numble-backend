package com.community.numble.app.board.domain;

import lombok.*;

import javax.persistence.*;
import java.util.*;
@Entity
@Getter @Setter
public class Post extends Time{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Long id;

	private String title;

	private String content;

	private String location;

	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
	private List<UploadFile> uploadFile = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<Comment> comment = new ArrayList<>();

	@OneToMany(mappedBy = "post")
	private List<PostLike> postlikes = new ArrayList<>();

	public void setMember(Member member){
		this.member = member;
		member.getPosts().add(this);
	}

}
