import { useState, useEffect } from "react";
import axios from 'axios';
import './Home.css';

export default function Home(){
    // 상태정의 state 필요
    // carlist
        // => 백앤드 스프링부트에서 받아온 차량의 목록
        // 데이터를 저장하는 변수.
    // setCarlist
        // => 데이터를 받아 온 후 , 화면을 다시 re-rendering 하기 위해 사용
    // 초기값 : 빈배열[]로 설정하여 데이터가 들어오기전 에러방지
    const [carlist, setCarlist]=useState([]);

    useEffect(()=>{
        // vite proxy 활용 /api/cars로 요청 보낸다
        // vite.config.js설정에 의해서
        // 'http://localhost:8090/api/cars' 로 전달된다.

        axios.get('/api/cars')
        .then((res)=>{
            // res.data에는 백앤드(스프링부트)에서 JSON 형태로 보낸
            // List<carProductDTO> 데이터가 담겨 있다.
            // console.log("받아온 데이터", res.data);
            // 받아온 데이터를 setCarlist에 저장 , 재랜더링
            setCarlist(res.data);
        })
        .catch((error) =>{
            console.log("데이러 로딩 에러" , error);
        })
    },[])
    return(
        <section>
            <div id="section_wrapHome">
                <div className="word">HOME</div>
                <div className="content">
                    <div className="carList">
                        {carlist.length>0 ? (
                            
                                carlist.map((car)=>(
                                    <div className="carItem" key={car.no}>
                                        <img src={`/img/car/${car.img}`} alt={car.carName}></img>
                                        <div className="carName">{car.carName} - {car.company}</div>
                                        <div className="carPrice">{Number(car.price).toLocaleString()}원</div>
                                    </div>
                                ))
                            
                        ):(
                            <><h3>등록된 차량이 없습니다.</h3></>
                        )}
                    </div>

                </div>

            </div>
        </section>
    )
}