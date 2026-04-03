import { useNavigate, useSearchParams } from "react-router-dom";

export default function SignupResult(){
    // 현재 URL ~~ result='값',
    // 에서 '값'을 읽기 위해 사용하는 훅
    const [searchParam] = useSearchParams();
    // result에는 success, duplicate, fail 이 담긴다.
    const result = searchParam.get("result");
    const navigate = useNavigate();

    return(
        <div id="section_wrap" style={{textAlign:'center'}}>
                {result === 'success' && <h2>회원가입 성공</h2>}
                {result === 'duplicate' && <h2>회원정보 중복</h2>}
                {result === 'fail' && <h2>회원가입 실패</h2>}
                {result === 'error' && <h2>서버 오류</h2>}

                <button type="button" onClick={()=>navigate('/member/login')}>
                    로그인 페이지로 이동
                </button>
        </div>

    )

}