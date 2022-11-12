package com.community.numble.app.board.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter @Setter
public class Post extends Time{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
		this.member = member;
		member.getPosts().add(this);
	}

	public void createType(String type){
		if ("NEW".equals(type)) this.setType(PostCategory.NEW);
		else if("EVENT".equals(type)) this.setType(PostCategory.EVENT);
		else if("SAIL".equals(type)) this.setType(PostCategory.SAIL);
		else if("MEETING".equals(type)) this.setType(PostCategory.MEETING);
		else if("LOST".equals(type)) this.setType(PostCategory.LOST);
		else if("FREE".equals(type)) this.setType(PostCategory.FREE);
		else throw new IllegalStateException("잘못된 카테고리정보 입니다.");
		
	}
}
