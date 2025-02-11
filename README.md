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

2025-01-13
1.로그인 요청:
    클라이언트가 /login 엔드포인트로 ID와 비밀번호를 전송 
2.Authentication Manager 호출:
    Spring Security가 AuthenticationManager를 통해 인증 요청을 처리
    AuthenticationManager는 모든 AuthenticationProvider를 순차적으로 호출.
3.CustomAuthenticationProvider 처리:
    authenticate 메서드에서 사용자 Id와 비밀번호를 검증 
    검증 성공 시 Authentication 객체 반환 
    실패 시 예외 발생 BadCredentialsException 또는 UsernameNotFoundException 
4.SecurityContext에 인증 정보 저장:
    인증 성공 시 반환된 Authentication 객체가 SecurityContext에 저장.

N+1문제란?
    한 번의 쿼리를 실행 후, 관련 엔티티를 로드하기 위해 추가로 N번의 쿼리가 실행되는 현상 
    부모-자식 관계에서 부모 리스트를 가져온 후, 각 부모의 자식을 조회하는 쿼리가 N번 실행되는 경우
해결방법)
    1.Fetch Join
        관련된 엔티티를 한 번의 쿼리로 가져온다. 
    2.Entity Graph 
        JPA에서 Fetch 전략을 정의하는 방법 
    3.Batch Size 
        Hibernate의 batch_size 설정을 사용하면 N+1 문제를 완화할 수 있다. 
        ex)spring.jpa.properties.hibernate.default_batch_fetch_size=10

2025-01-23 
대용량 데이터 처리
1.pagination
    데이터를 페이지 단위로 나누어 필요한 만큼만 가져오는 방법
2.Lazy loading
    관련 데이터를 즉시 로드하지 않고, 실제로 필요할 때 로드하는 전략

2025-02-11
1.Redis는 인메모리 데이터 저장소로, 빠른 속도를 기반으로 다양한 캐싱 작업에 적합 
JWT 저장, 세션 상태관리, 데이터베이스 쿼리 결과 캐싱과 같은 작업에 활용 
 
JWT를 Redis에 저장하는 이유 
    토큰 블랙리스트 
        로그아웃된 사용자의 JWT를 Redis에 저장하여 차단. 
        만료 전에 무효화해야 하는 토큰 관리 
    단기 만료 관리 
        JWT의 만료 시간을 Redis의 TTL(Time to live)로 설정하여 관리
     


