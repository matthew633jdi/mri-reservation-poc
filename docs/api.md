# API

# 1. Create Employee
- 직원(Employee)이 시스템에 회원 가입하기 위한 API
- EMR에 등록된 직원 ID(emrId)를 기준으로 계정을 생성한다.
- 동일한 emrId로 중복 가입은 허용하지 않는다.

## Request
```http request
POST /employees
Content-Type: application/json

{
  "emrId": "EMR00123",
  "name": "홍길동",
  "password": "plainPassword",
  "role": "STAFF"
}
```
### Fields
| 필드명      | 타입     | 필수 | 설명                   |
| -------- | ------ | -- | -------------------- |
| emrId    | String | Y  | EMR에 등록된 직원 ID (고유값) |
| name     | String | Y  | 직원 이름                |
| password | String | Y  | 로그인 비밀번호 (서버에서 암호화)  |
| role     | String | Y  | 직원 권한 역할             |

### Business Rules
- emrId는 반드시 유효한 값이어야 하며 중복될 수 없다.
- 비밀번호는 평문으로 저장하지 않으며, 서버에서 암호화 후 저장한다.
- 회원 가입 성공 시 직원 계정은 즉시 활성 상태로 생성된다.
- 회원 가입 시 자동으로 생성 시각(created_at)이 기록된다.

## Response
```http request
201 Created

{
  "id": 1,
  "emrId": "EMR00123",
  "name": "홍길동",
  "role": "STAFF",
  "createdAt": "2025-01-01T10:30:00"
}
```
### Fields
| 필드명       | 타입       | 설명        |
| --------- | -------- | --------- |
| id        | Long     | 내부 직원 식별자 |
| emrId     | String   | EMR 직원 ID |
| name      | String   | 직원 이름     |
| role      | String   | 권한 역할     |
| createdAt | DateTime | 계정 생성 시각  |

### Error Responses
1. emrId 중복
```http request
409 Conflict
{
  "code": "EMPLOYEE_ALREADY_EXISTS",
  "message": "이미 등록된 emrId입니다."
}
```
2. 필수 값 누락 또는 형식 오류 
```http request
400 Bad Request
{
  "code": "INVALID_REQUEST",
  "message": "요청 값이 올바르지 않습니다."
}

```

## Audit Log
회원 가입 성공 시 다음 정보가 감사 로그로 기록된다.
- 행위자: emrId
- 행위 유형: EMPLOYEE_SIGNUP
- 대상 데이터: 직원 ID
- 발생 시각