package com.hello.mybatistest.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Member {

	private final Long id;
	private String name;
	private String nickName;
	private BigDecimal amount;
	private LocalDateTime regDate;
}
