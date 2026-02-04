package com.green.member;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.green.member.mapper.MemberMapper;



// controller -> service : DAO 메소드 찾아있어 
// DAO야 메소드 있어 -> DB에서 찾아옴
// DB -> id, pw값들고  -> DAO로 보냄 -> service의 메소드로 보냄
// -> controller에게 id, pw 찾아서 보냄
@Service
public class MemberService {

	// id중복체크, 성공, 실패 상수변수 정의 
	// 회원가입의 중복을 확인하는 상수
	public final static int user_id_alreday_exit = 0;
	// 회원가입의 성공여부를 확인하는 상수
	public final static int user_id_success = 1;
	// 회원가입의 실패를 확인하는 상수
	public final static int user_id_fail = -1;
	

	@Autowired
	private MemberMapper memberMapper;
	
	//PasswordEncoder객체도 DI(의존객체)를 정의한다.
	@Autowired
	PasswordEncoder passwordEncoder;

	
	
	//회원가입이 제대로 되었는지, 혹은 회원가입이 실패했는지 예외처리
	public int signupConfirm(MemberDTO mdto) {
		System.out.println("MemberService signupConfirm()메소드 확인");
		
		//회원가입 중복체크
		//id 없음 => flase
		boolean isMember = memberMapper.isMember(mdto.getId());
		//회원가입 중복체크 통과했다면
		if(isMember == false) {
			
			
			// 문자인 pw를 암호화된 비밀번호로 변화해주는 코드
			// passwordEncoder.encode(null)안에 암호화 하고싶은 필드명 입력
			// Encode(암호화) : 인간언어 -> 기계어
			// Decode(복호화) : 기계어 -> 인간언어
			String encodepw = passwordEncoder.encode(mdto.getPw());
			
			// 암호화된 encodepw를 mdto.getPw() => 수정
			mdto.setPw(encodepw);
			
			// 중복된 아이디가 존재하지 않을 때 DB에 회원의 정보가 추가된다.
			// DB에 회원정보가 추가되는 부분 => 암호화가 이루어져야 한다.
			int result = memberMapper.insertMember(mdto);
			if(result > 0 ) {
				return user_id_success; // result = 1
			}else {
				return user_id_fail; // result = -1
			}
		}else {
			// 중복된 아이디가 존재할 때
			return user_id_alreday_exit; // result = 0;
		}
	}


	// 암호화된 DB를 복호화하여 로그인하는 메소드
	public MemberDTO loginConfirm(MemberDTO mdto) {
		System.out.println("MemberService loginConfirm()메소드 확인");
		
		// 1. DB에서 해당정보의 id가져오기
		MemberDTO dbMember = memberMapper.oneSelectMember(mdto.getId());
		
		// 2. DB에서 꺼내온 id의 비밀번호와 입력한 값이 일치 하는지확인
		// 암호화된 데이터를 PasswordEncoder.matches(사용자 입력한 문, DB에 저장된 암호문)
		// PasswordEncoder.matches() => 복호화한 것이다.
		if(dbMember != null && dbMember.getPw() != null) {
			// 복호화 시켜 비교하는 중..
			if(passwordEncoder.matches(mdto.getPw(), dbMember.getPw())) {
				// 로그인 성공한경우
				return dbMember;
			}
		}
		return null; // 로그인 실패 
	}
	
	
}