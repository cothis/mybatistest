package com.hello.mybatistest.controller.dto;

import com.hello.mybatistest.domain.Member;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
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

	@ConstructorProperties({"id", "name", "nickName", "amount", "regDate"})
	public MemberDto(Long id, String name, String nickName, BigDecimal amount, LocalDateTime regDate) {
		this.id = id;
		this.name = name;
		this.nickName = nickName;
		this.amount = amount;
		this.regDate = regDate;
	}
}
