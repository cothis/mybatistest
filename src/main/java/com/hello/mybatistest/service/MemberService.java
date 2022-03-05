package com.hello.mybatistest.service;

import com.hello.mybatistest.domain.Member;
import com.hello.mybatistest.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	public List<Member> findAll() {
		return memberRepository.findAll();
	}
}
