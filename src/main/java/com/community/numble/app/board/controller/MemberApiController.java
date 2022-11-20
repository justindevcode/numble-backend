package com.community.numble.app.board.controller;

import com.community.numble.app.board.domain.Member;
import com.community.numble.app.board.dto.MemberDto.CreateMemberRequest;
import com.community.numble.app.board.dto.MemberDto.CreateMemberResponse;
import com.community.numble.app.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberApiController {


	private final MemberService memberService;

	@PostMapping("/numble11/members")
	public ResponseEntity saveMember(@RequestBody CreateMemberRequest request) {
		Member member = new Member();
		member.setName(request.getName());

		Long id = memberService.join(member);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new CreateMemberResponse(id));
	}

	@PostMapping("/numble11/members2")
	public CreateMemberResponse saveMemberv2(@RequestBody CreateMemberRequest request) {
		Member member = new Member();
		member.setName(request.getName());

		Long id = memberService.join(member);
		return new CreateMemberResponse(id);
	}
}
