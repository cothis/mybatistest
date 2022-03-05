package com.hello.mybatistest.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
@Alias("Member")
public class Member {

	private Long id;
	private String name;
	private BigDecimal amount;
}
