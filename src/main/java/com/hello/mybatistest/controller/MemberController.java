package com.hello.mybatistest.controller;

import com.hello.mybatistest.controller.dto.MemberDto;
import com.hello.mybatistest.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("members")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@GetMapping
	public List<MemberDto> members() {
		return memberService.findAll().stream()
				.map(MemberDto::new)
				.collect(Collectors.toList());
	}
}
