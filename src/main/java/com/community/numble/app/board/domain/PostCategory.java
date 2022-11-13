package com.community.numble.app.board.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
public enum PostCategory {
	NEW("새글"),EVENT("이벤트"),SAIL("할인"),MEETING("모임"),LOST("분실"),FREE("자유");

	String value;

	PostCategory(String value) {
		this.value = value;
	}
}
