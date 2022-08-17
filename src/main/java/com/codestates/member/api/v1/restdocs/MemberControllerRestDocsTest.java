package com.codestates.member.api.v1.restdocs;

import com.codestates.member.entity.Member;
import com.codestates.member.controller.MemberController;
import com.codestates.member.mapper.MemberMapper;
import com.codestates.member.service.MemberService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class MemberControllerRestDocsTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberMapper mapper;

    @Autowired
    private Gson gson;

    @Test
    public void getMemberTest() throws Exception {
        // given
        MemberDto.response responseDto =
                new MemberDto.response(1L, "김코딩", "m", "프로젝트스테이츠", 005, 001);

        // willReturn()이 최소 null은 아니어야 한다.
           given(mapper.memberToMemberResponse(Mockito.any(Member.class))).willReturn(responseDto);

        // when
        ResultActions actions =
                mockMvc.perform(
                        get("/v11members")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.memberId").value(get.getEmail()))
                .andExpect(jsonPath("$.data.name").value(get.getName()))
                .andExpect(jsonPath("$.data.sex").value(get.getSex()))
                .andExpect(jsonPath("$.data.companyName").value(get.getCompanyName()))
                .andExpect(jsonPath("$.data.companyType").value(get.getCompanyType()))
                .andExpect(jsonPath("$.data.companyLocation").value(get.getCompanyLocation()))
                .andDo(document("get-member",    // =========== (1) API 문서화 관련 코드 시작 ========
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                List.of(
                                        fieldWithPath("memberId").type(JsonFieldType.STRING).description("Id"),
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                                        fieldWithPath("sex").type(JsonFieldType.STRING).description("성별"),
                                        fieldWithPath("companyName").type(JsonFieldType.STRING).description("회사 이름"),
                                        fieldWithPath("companyType").type(JsonFieldType.STRING).description("회사 타입"),
                                        fieldWithPath("companyLocation").type(JsonFieldType.STRING).description("회사 위치")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data.name").type(JsonFieldType.STRING).description("이름"),
                                        fieldWithPath("data.sex").type(JsonFieldType.STRING).description("성별"),
                                        fieldWithPath("companyName").type(JsonFieldType.STRING).description("회사 이름"),
                                        fieldWithPath("companyType").type(JsonFieldType.STRING).description("회사 타입"),
                                        fieldWithPath("companyLocation").type(JsonFieldType.STRING).description("회사 위치")
                                )
                        )
                ));   // =========== (2) API 문서화 관련 코드 끝========
    }
}