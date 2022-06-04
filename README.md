# 도시 조회 API


## 사용 기술
- Java, Spring boot, JPA, Spring Security, jwt, MySQL, MyBatis

## 시나리오
### 1. Spring boot 구동 
- 1-1. ApplicationRunner
    - 회원 데이터 추가
    
- 1-2. data.sql / schema.sql 
  - 테이블 생성, 데이터 INSERT

### 2. 로그인 (MemberController)
- Spring Security + jwt 이용하여 로그인 기능 구현
- password -> PasswordEncoder 이용하여 암호화 처리
- `로그인 API` 호출 -> response `토큰` 발급
####example
```
//URL : POST /login
{
    "id":"pyk",
    "password":"test1234"
}
//response
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJweWsiLCJpYXQiOjE2NDAyMzcwNDAsImV4cCI6MTY0MDI0MDY0MH0.rnxMQv3Z3t5WtYGPjqRH5AYMluwXVz292DD9IWWLZus"
}
```

### 3. 여행 (TravelController)
- `로그인 API` 호출 -> `여행 API` 호출
- 로그인 API 에서 발급 받은 토큰 값을 header -> `X-AUTH-TOKEN` 추가 후 여행 조회 API 호출
- `405` -> 하나의 도시에는 하나의 여행만 등록 가능
- `406` -> 여행 시작날짜가 오늘보다 적을 경우
####example
```
//URL : GET /travel/{memberId}
[
    {
        "id": 1,
        "memberId": "pyk",
        "cityId": 1,
        "startDate": "2022-06-22",
        "endDate": "2022-06-24",
        "registerDate": "2022-06-01T12:12:12"
    },
    {
        "id": 2,
        "memberId": "pyk",
        "cityId": 2,
        "startDate": "2022-07-22",
        "endDate": "2022-07-24",
        "registerDate": "2022-06-02T12:12:12"
    }
]
```
```
//URL : POST /travel/{cityId}/{memberId}
//response
{
    "message": "여행 등록이 완료되었습니다."
}
```

### 4. 도시 (CityController)
- `로그인 API` 호출 -> `도시 API` 호출
- 로그인 API 에서 발급 받은 토큰 값을 header -> `X-AUTH-TOKEN` 추가 후 도시 API 호출
- 해당 회원의 모든 휴가 목록이 반환 됨
- 해당 회원의 휴가 목록이 없을 경우 status `404` 리턴
####example
```
//URL : GET /city/{memberId}

//response
[
    {
        "id": 1,
        "name": "강릉",
        "address": "강릉시",
        "comment": "바다가고싶다",
        "registerDate": "2022-06-02T12:12:12"
    }
]
```
```
//URL : POST /city
//response
{
    "message": "도시 등록이 완료되었습니다."
}
```