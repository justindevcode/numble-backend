package com.community.numble.app.board.controller;

import com.community.numble.app.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApiController {


	private final MemberService memberService;

	@PostMapping("/numble11/members")
	public ResponseEntity saveMember()
}
