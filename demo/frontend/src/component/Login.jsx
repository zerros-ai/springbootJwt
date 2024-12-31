import React, { useState } from "react";

function Login() {
    const [id, setId] = useState("");
    const [password, setPassword] = useState("");
    const [errorMessage, setErrorMessage] = useState("");

    //사용자 등록 팝업 상태
    const [showRegistPopup, setShowRegistPopup] = useState("");
    const [newUser, setNewUser] = useState("");
    const [registerMessage, setRegisterMessage] = useState("");

    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch("http://localhost:8080/api/auth/login", {
                method: "POST",
                headers: {
                    "Content-Type":"application/json",
                },
                body:JSON.stringify({id,password}),
            });
            if(response.ok) {
                const data = await response.json();
                localStorage.setItem("token", data.token);
                alert("로그인 성공");
                window.location.href = "/dashboard";
            }else {
                const errorData = await response.json();
                setErrorMessage(errorData.message||"로그인 실패!");
            }
        }catch (error) {
            setErrorMessage("서버와 연결할 수 없습니다.")
        }
    };

    const handleRegister = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch ("http://localhost:8080/api/users/add", {
                method: "POST",
                headers: {
                    "Content-Type" : "application/json",
                },
                body:JSON.stringify(newUser),
            });
            if(response.ok){
                setRegisterMessage("사용자가 성공적으로 등록되었습니다!");
                setNewUser({id: "",password:""/** , role: "USER" **/});
                setShowRegistPopup(false);
            }else {
                const errorData = await response.json();
                setRegisterMessage(errorData.message || "사용자 등록 실패!");
            }
        }catch (e) {
            setRegisterMessage("서버와 연결할 수 없습니다.");
        }
    }

    return (
        <div style={{maxWidth: "400px", margin: "50px auto", textAlign: "center"}}>
            <h1>로그인</h1>
            <form onSubmit={handleLogin}>
                <div>
                    <input
                        type="text"
                        placeholder="사용자 이름"
                        value={id}
                        onChange={(e) => setId(e.target.value)}
                        required
                        style={{width: "100%", padding: "10px", marginBottom: "10px"}}
                    />
                </div>
                <div>
                    <input
                        type="password"
                        placeholder="비밀번호"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                        style={{width: "100%", padding: "10px", marginBottom: "20px"}}
                    />
                </div>
                <button type="submit" style={{padding: "10px 20px", width: "100%"}}>
                    로그인
                </button>
            </form>
            {errorMessage && (
                <p style={{color: "red", marginTop: "20px"}}>{errorMessage}</p>
            )}
            <button
                onClick={() => setShowRegisterPopup(true)}
                style={{ padding: "10px 20px", marginTop: "20px", width: "100%" }}
            >
                사용자 등록
            </button>

            {showRegisterPopup && (
                <div
                    style={{
                        position: "fixed",
                        top: "50%",
                        left: "50%",
                        transform: "translate(-50%, -50%)",
                        background: "white",
                        padding: "20px",
                        boxShadow: "0 0 10px rgba(0,0,0,0.5)",
                        borderRadius: "5px",
                    }}
                >
                    <h2>사용자 등록</h2>
                    <form onSubmit={handleRegister}>
                        <div>
                            <input
                                type="text"
                                placeholder="사용자 이름"
                                value={newUser.username}
                                onChange={(e) =>
                                    setNewUser({ ...newUser, username: e.target.value })
                                }
                                required
                                style={{
                                    width: "100%",
                                    padding: "10px",
                                    marginBottom: "10px",
                                }}
                            />
                        </div>
                        <div>
                            <input
                                type="password"
                                placeholder="비밀번호"
                                value={newUser.password}
                                onChange={(e) =>
                                    setNewUser({ ...newUser, password: e.target.value })
                                }
                                required
                                style={{
                                    width: "100%",
                                    padding: "10px",
                                    marginBottom: "10px",
                                }}
                            />
                        </div>
                        <button
                            type="submit"
                            style={{ padding: "10px 20px", width: "100%" }}
                        >
                            등록
                        </button>
                        <button
                            type="button"
                            onClick={() => setShowRegisterPopup(false)}
                            style={{
                                padding: "10px 20px",
                                width: "100%",
                                marginTop: "10px",
                                background: "gray",
                                color: "white",
                            }}
                        >
                            취소
                        </button>
                    </form>
                    {registerMessage && (
                        <p style={{ color: "red", marginTop: "10px" }}>{registerMessage}</p>
                    )}
                </div>
            )}
        </div>
    );
}

export default Login;