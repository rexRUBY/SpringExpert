# SPRING PLUS


## 2. 성능 개선 결과 정리
### 2.1 테스트 설정
- **스레드 그룹 설정**
    - **Number of Threads (users)**: 100
    - **Ramp-up Period (seconds)**: 1
    - **Loop Count**: 10

위와 같은 설정으로 100명의 사용자가 점진적으로 접속하여 부하를 주는 방식으로 성능을 측정함.

### 2.2 성능 개선 과정 및 결과
성능 개선 과정은 기존의 조회 성능을 개선하기 위해 데이터베이스 인덱스를 추가하는 등의 최적화 작업을 진행하였습니다. 각 단계별로 조회 속도의 변화를 아래 표에 정리하였습니다.

| 단계                | 적용한 최적화 방법      | 평균 조회 속도 (초) |
|-------------------|----------------------|--------------|
| 최초 조회          | 최적화 전 (기본 설정)  | 5.2          |
| 인덱스 추가 후     | `email` 및 `nickname` 컬럼에 인덱스 추가 | 2.6          |
| 추가 최적화 적용 후 | 불필요한 데이터 로드 최소화 및 캐싱 적용 | 2.6          |

### 2.3 인덱스 추가 코드
다음은 `User` 엔티티에 인덱스를 추가한 코드 예시입니다.

```java
@Entity
@Table(name = "users", indexes = {
    @Index(name = "idx_user_email", columnList = "email"),
    @Index(name = "idx_user_nickname", columnList = "nickname")
})
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 기타 필드 및 메서드
}
```

### 2.4 성능 개선 그래프
아래 그래프는 각 단계별 평균 조회 속도의 변화입니다.

1. 인덱스 적용 전
   <img width="1030" alt="스크린샷 2024-10-11 오전 4 33 44" src="https://github.com/user-attachments/assets/f931ea54-6b3c-40a8-81f1-32fc76d6024f">
   ![image](https://github.com/user-attachments/assets/05e7e880-93d4-49fd-955c-84e980e3ab4b)

2. 인덱스 적용 후
   <img width="1178" alt="스크린샷 2024-10-11 오전 4 46 50" src="https://github.com/user-attachments/assets/e6c4bf54-658c-402d-8ff3-97ac720d362b">
   ![image](https://github.com/user-attachments/assets/a80a5205-bb07-4515-90f3-efb5e34a93c3)

