package com.green.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.green.member.MemberDTO;

@Mapper
public interface MemberMapper {
	// 회원 가입
	public int insertMember(MemberDTO mdto);
	public boolean isMember(String id);
	public MemberDTO oneSelectMember(String id);
}
