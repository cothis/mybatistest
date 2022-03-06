package com.hello.mybatistest.repository;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@TestConfiguration
@Slf4j
public class ContextConfig {

	@Bean
	SqlSessionFactory sessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(dataSource);
		factory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("mapper/*Mapper.xml"));
		factory.setTypeAliasesPackage("com.hello.mybatistest.domain");
		Configuration configuration = new Configuration();
		configuration.setMapUnderscoreToCamelCase(true);
		configuration.setUseGeneratedKeys(true);
		configuration.setJdbcTypeForNull(JdbcType.NULL);
		factory.setConfiguration(configuration);
		return factory.getObject();
	}

	@Bean
	JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	SqlSession sqlSession(SqlSessionFactory sessionFactory) {
		return sessionFactory.openSession();
	}

	@Bean
	PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	MemberRepository memberRepository(SqlSessionFactory sessionFactory) throws Exception {
		MapperFactoryBean<MemberRepository> factory = new MapperFactoryBean<>(MemberRepository.class);
		factory.setSqlSessionFactory(sessionFactory);
		return factory.getObject();
	}

}
