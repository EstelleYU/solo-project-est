package com.codestates.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member{
    private long memberId;
    private String name;
//    private String password;
    private String sex;
    private String company_name;
    private long company_type;
    private long company_location;
}

