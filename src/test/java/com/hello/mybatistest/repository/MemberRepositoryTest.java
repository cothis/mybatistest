package com.hello.mybatistest.repository;

import com.hello.mybatistest.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Slf4j
@Transactional
@AutoConfigureMybatis
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	DataSourceTransactionManager tm;

	@Autowired
	SqlSession session;

	Connection connection;

	@BeforeEach
	void testData() throws SQLException {
		connection = session.getConnection();
		try (Statement statement = connection.createStatement()) {
			statement.execute("INSERT INTO MEMBER(name, nick_name, amount) VALUES('test1', 'test-nickname', 10.5)");
		}
	}

	@Test
	void findAll() {
		List<Member> all = memberRepository.findAll();
		for (Member member : all) {
			log.info("member = {} ", member);
		}
	}
}