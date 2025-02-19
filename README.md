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
 Redis는 따로 서버를 구축해야됨
JWT를 Redis에 저장하는 이유 
    토큰 블랙리스트 
        로그아웃된 사용자의 JWT를 Redis에 저장하여 차단. 
        만료 전에 무효화해야 하는 토큰 관리 
    단기 만료 관리 
        JWT의 만료 시간을 Redis의 TTL(Time to live)로 설정하여 관리
     
2025-02-18
    엔드포인트란? 클라이언트와 서버간 데이터를 주고받는 URL 주소 
        ex)API URL이라고 보면 된다.
    Spring Boot Actuator이란?
    애플리케이션의 상태와 성능을 쉽게 모니터링할 수 있도록 다양한 엔드포인트를 제공하는 라이브러리 
    Zipkin도 서버다 

    Docker란?
        컨테이너 기술을 이용해 어플을 패키징, 배포, 실행할 수 있는 플랫폼 
        이미지:실행 가능한 애플리케이션과 그에 필요한 환경을 포함한 템플릿
        컨테이너:이미지를 기반으로 실행되는 독립적인 환경 
        도커 허브:공개된 도커 이미지를 저장하고 공유하는 저장소 
        docker file:이미지를 빌드할때 사용하는 설정 파일 
        volume:컨테이너의 데이터를 저장하는 방법
    
    API 버전관리 
        기존 API를 유지하면서 새로운 기능 추가 
        클라이언트가 특정 버전의 API를 사용할 수 있도록 지원 
        변경 사항이 있을 때, 기존 사용자를 깨지 않도록 보장 
        1)URL 버전 관리 
            경로에 버전 추가하기 /api/v1/user
        2)요청 헤더 기반 버전 관리
            @GetMapping
            public String getUsers(@RequestHeader(value = "API-Version", defaultValue = "1") String version) {
                if ("2".equals(version)) {
                    return "User list for v2";
                }
                return "User list for v1";
            }
        3)파라미터 기반 버전 관리 
            파라미터에 버전을 추가 
    API 문서화 
        API 사용법을 쉽게 이해할 수 있음 
        자동으로 API 명세를 생성할 수 있음 
        클라이언트와 원활한 협업 가능 
    GraphQL
        클라이언트가 필요한 데이터만 요청할 수 있는 API 방식 
2025-02-19
    GraphQL 
        REST API 처럼 개별 API가 아닌 /graphql에서 모든 처리를 함 
        @Controller 와 @QueryMapping 어노테이션으로만 사용 해야 요청이 동작 
        Query:조회
        Mutation:삽입/수정/삭제