package com.hello.mybatistest.repository;

import com.hello.mybatistest.config.DataSourceConfig;
import com.hello.mybatistest.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Transactional
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DataSourceConfig.class, ContextConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PureMemberRepositoryTest {

	// Todo 제발 살려줘요..

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	JdbcTemplate jdbcTemplate;

	private void insert(Member member) {
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("MEMBER").usingGeneratedKeyColumns("id");
		HashMap<String, Object> params = new HashMap<>();
		params.put("name", member.getName());
		params.put("nick_name", member.getNickName());
		params.put("amount", member.getAmount());
		params.put("reg_date", member.getRegDate());

		Number number = jdbcInsert.executeAndReturnKey(params);
		member.setId(number.longValue());
	}

	@Test
	void findAll() {
		// given
		Member member1 = new Member("test1", "nickname1", new BigDecimal("1.1234"));
		insert(member1);
		Member member2 = new Member("test2", "nickname2", new BigDecimal("10.9999"));
		insert(member2);
		Member member3 = new Member("test3", "nickname3", new BigDecimal("0.0000"));
		insert(member3);

		// when
		List<Member> members = memberRepository.findAll();
		log.info("members = {} ", members);

		// then
		assertThat(members).containsExactly(member1, member2, member3);
	}
}