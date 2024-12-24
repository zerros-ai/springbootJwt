import React, {useEffect} from "react";
import {useNavigate} from "react-router-dom";

function Dashboard(){
    const navigate = useNavigate();

    useEffect(() => {
        const token = localStorage.getItem("token");
        if(!token){
            alert("로그인이 필요합니다.");
            navigate("/");
        }
    }, [navigate]);
    return (
        <div style={{textAlign: "center", marginTop: "50px"}}>
            <h1>대시보드</h1>
            <p>여기는 로그인 후 접근할 수 있는 페이지입니다.</p>
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