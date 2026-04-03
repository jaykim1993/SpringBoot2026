import { useState, useContext } from 'react';
import { AuthContext } from '../contexts/AuthContext';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './Member.css';

export default function Login() {
  const {login} = useContext(AuthContext);
  const [inputId, setInputId] = useState('');
  const [inputPw, setInputPw] = useState('');
  const navigate = useNavigate();

  const loginHandler =()=>{
    if(inputId === ''){
            alert('아이디 입력하세요');
            return;
        }
        if(inputPw === ''){
            alert('비밀번호를 입력하세요');
            return;
        }

        axios.post('/api/member/login', {id:inputId, pw:inputPw})
        .then((res)=>{
          console.log('res.data',res.data);
          if(res.data){
            alert(`${res.data.id}님 환영합니다`);
            navigate('/');
            // AuthContext login 함수에 id 넣는다.
            login(res.data.id);
          } else {
            alert('아이디와 비밀번호를 확인하세요');
          }
        })
        .catch((error)=>{
          console.log(error);
        })
  }

  return (
    <div id="section_wrap">
      <div className="word">로그인</div>

      <table width="500" border="1">
        <tbody>
          <tr>
            <td>아이디</td>
            <td>
              <input 
              type="text"  
              name='inputId'
              value={inputId}
              onChange={(e)=>setInputId(e.target.value)}
              />
            </td>
          </tr>

          <tr>
            <td>비밀번호</td>
            <td>
              <input 
              type="password"  
              name='inputPw'
              value={inputPw}
              onChange={(e)=>setInputPw(e.target.value)}
              />
            </td>
          </tr>

          <tr>
            <td colSpan="2" align="center">
              <button onClick={loginHandler}>로그인</button>
              <button type="reset">취소</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  );
}