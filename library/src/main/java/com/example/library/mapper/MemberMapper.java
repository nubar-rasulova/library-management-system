package com.example.library.mapper;

import com.example.library.dto.MemberDTO;
import com.example.library.entity.Member;

public class MemberMapper {
    public static MemberDTO toDTO(Member member) {
        return MemberDTO.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .build();
    }

    public static Member toEntity(MemberDTO memberDTO) {
        return Member.builder()
                .id(memberDTO.getId())
                .name(memberDTO.getName())
                .email(memberDTO.getEmail())
                .build();
    }
}
