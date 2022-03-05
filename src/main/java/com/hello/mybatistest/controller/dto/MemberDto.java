package com.hello.mybatistest.controller.dto;

import com.hello.mybatistest.domain.Member;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MemberDto {
	private Long id;
	private String name;
	private BigDecimal amount;

	public MemberDto(Member member) {
		id = member.getId();
		name = member.getName();
		amount = member.getAmount();
	}
}
