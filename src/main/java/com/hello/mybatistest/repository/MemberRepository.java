package com.hello.mybatistest.repository;

import com.hello.mybatistest.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberRepository {

	List<Member> findAll();
}
