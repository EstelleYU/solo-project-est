package com.codestates.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponseDto {
    private long memberId;
    private String name;
    //    private String password;
    private String sex;
    private String company_name;
    private long company_type;
    private long company_location;
}