package com.hello.mybatistest.controller.dto;

import com.hello.mybatistest.domain.Member;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class MemberDto {
	private final Long id;
	private final String name;
	private final String nickName;
	private final BigDecimal amount;
	private final LocalDateTime regDate;

	public MemberDto(Member member) {
		id = member.getId();
		name = member.getName();
		nickName = member.getNickName();
		amount = member.getAmount();
		regDate = member.getRegDate();
	}
}
