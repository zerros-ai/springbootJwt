2025-01-06
배운점
- jwt와 jws의 차이
    JWT는 Json Web Token의 기본 형태로 서명이 없어 데이터의 무결성을 보장하지 못함
    JWS는 JWT에 서명을 추가한 형태로 데이터의 무결성과 발신자의 신뢰성을 보장한다.
잘못한 점: 토큰은 서명을 추가해 JWS형태로 발급했는데 토큰 유효성 검사는 JWT형태로 검사해 false를 리턴했다. 

2025-01-08
ROLE 테이블 만들기

2025-01-09
sercurityConfig에서 .hasRole 과 .hasAuthority의 차이점 
.hasRole은 ROLE_ 접두사를 붙여 DB에서 검색. 
.hasAuthority는 그대로 DB에서 검색.