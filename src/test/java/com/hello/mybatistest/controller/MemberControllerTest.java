package com.hello.mybatistest.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hello.mybatistest.controller.dto.MemberDto;
import com.hello.mybatistest.domain.Member;
import com.hello.mybatistest.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@Slf4j
class MemberControllerTest {

	@Mock
	MemberService memberService;

	@InjectMocks
	MemberController memberController;

	MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
	}

	List<Member> createMembers() {
		List<Member> members = new ArrayList<>();
		members.add(new Member("test1", "nickname1", new BigDecimal("1.1234")));
		members.add(new Member("test2", "nickname2", new BigDecimal("10.9876")));
		members.add(new Member("test3", "nickname3", new BigDecimal("0")));
		return members;
	}

	@Test
	@DisplayName("memberService가 반환하는 members를 memberDto로 잘 변환해서 반환한다.")
	void members() throws Exception {
		// given
		List<Member> members = createMembers();
		doReturn(members).when(memberService).findAll();

		// when
		ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders.get("/members")
						.contentType(MediaType.APPLICATION_JSON)
		);

		// then
		List<MemberDto> expectedMembers = members.stream().map(MemberDto::new).collect(Collectors.toList());

		MvcResult mvcResult = resultActions.andExpect(status().isOk()).andReturn();
		String json = mvcResult.getResponse().getContentAsString();
		TypeReference<List<MemberDto>> memberDtoListTypeReference = new TypeReference<List<MemberDto>>() {
		};
		List<MemberDto> result = objectMapper.readValue(json, memberDtoListTypeReference);

		assertThat(result).containsExactlyElementsOf(expectedMembers);
	}
}