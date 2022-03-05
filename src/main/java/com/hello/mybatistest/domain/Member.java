package com.hello.mybatistest.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Member {
	private Long id;
	private String name;
	private String nickName;
	private BigDecimal amount;
	private LocalDateTime regDate;

	public Member(String name, String nickName, BigDecimal amount) {
		this.name = name;
		this.nickName = nickName;
		this.amount = amount;
	}
}
