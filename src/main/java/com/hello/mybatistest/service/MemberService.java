package com.hello.mybatistest.service;

import com.hello.mybatistest.domain.Member;
import com.hello.mybatistest.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberService {

	private final MemberRepository memberRepository;

	public List<Member> findAll() {
		return memberRepository.findAll();
	}
}
