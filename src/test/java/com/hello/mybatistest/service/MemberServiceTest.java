package com.hello.mybatistest.service;

import com.hello.mybatistest.domain.Member;
import com.hello.mybatistest.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

class MemberServiceTest {

	MockMemberRepository memberRepository = new MockMemberRepository();
	MemberService memberService = new MemberService(memberRepository);

	static class MockMemberRepository implements MemberRepository {
		private final List<Member> members = new ArrayList<>();

		public void insert(Member member) {
			members.add(member);
		}

		public void clear() {
			members.clear();
		}

		@Override
		public List<Member> findAll() {
			return members;
		}
	}

	@AfterEach
	void clear() {
		memberRepository.clear();
	}

	void insert() {
		memberRepository.insert(new Member("test1", "nickname1", new BigDecimal("1.1234")));
		memberRepository.insert(new Member("test2", "nickname2", new BigDecimal("0.0000")));
		memberRepository.insert(new Member("test3", "nickname3", new BigDecimal("1")));
	}

	@Test
	void findAll() {
		// given
		insert();

		// when
		List<Member> findMembers = memberService.findAll();

		// then
		Assertions.assertThat(findMembers)
				.extracting("name")
				.contains("test1", "test2", "test3");
		Assertions.assertThat(findMembers)
				.extracting("nickName")
				.contains("nickname1", "nickname2", "nickname3");
		Assertions.assertThat(findMembers)
				.extracting("amount")
				.contains(new BigDecimal("1.1234"), new BigDecimal("0.0000"), new BigDecimal("1"));
	}

}