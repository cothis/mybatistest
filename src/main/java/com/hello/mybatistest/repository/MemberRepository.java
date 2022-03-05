package com.hello.mybatistest.repository;

import com.hello.mybatistest.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemberRepository {

	@Select("SELECT id, name, amount FROM MEMBER")
	List<Member> findAll();
}
