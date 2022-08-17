package com.codestates.member.service;

import com.codestates.member.entity.Member;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberService {

    public Member findMember(long memberId){
        Member member = new Member(1L, "김코딩", "m", "프로젝트스테이츠", 005, 001);
        return member;
    }

    public List<Member> findMembers(){
        List<Member> members = List.of(
                new Member(1L, "김코딩", "m", "프로젝트스테이츠", 005, 001),
                new Member(2L, "이자바", "m", "프로젝트스테이츠", 005, 001)
        );
        return members;
    }

}
