# 예약 앱 어드민

미용실 예약 관리자 앱 입니다.

RestTemplate을 통해 미용실 예약 앱과 통신합니다. 전체 예약 조회부터 회원 별 예약, 날짜 별 예약을 조회/수정/삭제 할 수 있습니다.

방문이 끝난 예약은 방문 완료로 상태를 변경할 수 있습니다.

- 깃 허브 주소 : https://github.com/hingjae/reservation-admin
- 도메인 : https://reservation-admin-062f5d525c74.herokuapp.com/

### 사용 기술

- 언어 : java 17
- 프레임워크 : spring boot 2.7
- DB : mysql
- DB접근 기술 : jpa
- 웹 프론트 : thymeleaf, bootstrap
- 베포 : heroku

### ERD

![ERD](https://raw.githubusercontent.com/hingjae/reservation/76fb1278d9c2712c0fc16a35fd3372cf36b8aaf8/document/%08erd.svg)

### 유즈케이스
![ERD](https://raw.githubusercontent.com/hingjae/reservation-admin/84950881a14f95cfa13f9e68c58492427e20341d/docs/usecase.svg)