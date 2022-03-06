package com.hello.mybatistest.controller;

import com.hello.mybatistest.controller.dto.MemberDto;
import com.hello.mybatistest.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("members")
@Slf4j
public class MemberController {

	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
		log.info("MemberController created");
	}

	@GetMapping
	public List<MemberDto> members() {
		return memberService.findAll()
				.stream()
				.map(MemberDto::new)
				.collect(Collectors.toList());
	}
}
