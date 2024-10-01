package com.example.library.service.impl;

import com.example.library.dto.MemberDTO;
import com.example.library.entity.Member;
import com.example.library.exception.ResourceNotFoundException;
import com.example.library.mapper.MemberMapper;
import com.example.library.repository.MemberRepository;
import com.example.library.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public MemberDTO createMember(MemberDTO memberDTO) {
        Member member = MemberMapper.toEntity(memberDTO);
        member = memberRepository.save(member);
        return MemberMapper.toDTO(member);
    }

    @Override
    public MemberDTO getMemberById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));
        return MemberMapper.toDTO(member);
    }

    @Override
    public List<MemberDTO> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(MemberMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MemberDTO updateMember(Long id, MemberDTO memberDTO) {
        Member existingMember = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

        existingMember.setName(memberDTO.getName());
        existingMember.setEmail(memberDTO.getEmail());

        existingMember = memberRepository.save(existingMember);
        return MemberMapper.toDTO(existingMember);
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
