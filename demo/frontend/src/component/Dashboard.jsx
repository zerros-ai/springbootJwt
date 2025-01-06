import React, {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";

function Dashboard(){
    const navigate = useNavigate();
    const [userInfo, setUserInfo] = useState(null);

    useEffect(() => {
        const token = localStorage.getItem("token");
        if(!token){
            alert("로그인이 필요합니다.");
            navigate("/");
        } else {
            fetch("http://localhost:8080/api/users/info",{
                method:"GET",
                headers:{
                    Authorization:"Bearer " + token,
                },
            })
                .then((response)=>{
                    if(response.ok){
                        return response.json();
                    }else{
                        throw new Error("Failed to fetch user info");
                    }
                })
                .then((data)=> setUserInfo(data))
                .catch((error)=>{
                    console.error("Error fetching user info: ",error);
                    localStorage.removeItem("token");
                    navigate("/");
                });
        }
    }, [navigate]);
    return (
        <div style={{textAlign: "center", marginTop: "50px"}}>
            <h1>대시보드</h1>
            <p>여기는 로그인 후 접근할 수 있는 페이지입니다.</p>
            {userInfo && (
                <div>
                    <h2>사용자 정보</h2>
                    <p>ID: {userInfo.id}</p>
                    <p>이름: {userInfo.name}</p>
                    <p>메시지: {userInfo.message}</p>
                </div>
            )}
            <button
                onClick={() => {
                    localStorage.removeItem("token");
                    navigate("/");
                }}
            >
                로그아웃
            </button>
        </div>
    );
}
export default Dashboard;