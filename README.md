# 아파트 실거래가 조회 'HowMuchMyHouse' 

- 프로젝트 기간 : 3일

## Tech Stack

- JAVA Spring
- H2 DB

## 구동 순서

1. 클라이언트가 url(http://localhost:8080/basic/start?sigungu=서울특별시 성북구&eupmyundong=보문동6가&aptName=보문파크뷰자이&date=202205)로 통해 조회할 아파트 입력
2. 먼저 DB에 접근하여 아파트 거래 정보가 있는지 확인
3. 없을 시 OPEN API에서 정보를 가져와 파싱 후 데이터 제공
4. 제공된 데이터는 DB에 저장

## 실행 시 유의사항

- application.properties 파일의 my.apiKey에 apiKey를 입력하세요.
- DB 테이블은 hmmh\sql\CreateTable.sql을 참조하세요.
- 서울특별시를 조회하고 DB에 저장될 시 서울로 저장되어 AptRepositoryImpl에 조회 시 replace를 해주었는데, 다른 지역 조회 시 오류가 발생하면 조회될 때 시군구 이름과 DB에 저장되는 시군구 이름을 비교해보세요.

## 실행 화면

- 현재 DB는 비어있다.
![image](https://user-images.githubusercontent.com/90389323/193715693-c455fada-0bbb-41f1-a169-893a286e3e6f.png)


- url로 접속

![image](https://user-images.githubusercontent.com/90389323/193715605-7aae2b72-5144-41fe-b17d-6fffb1c456e9.png)

- 콘솔창

![image](https://user-images.githubusercontent.com/90389323/193715806-26a811ba-53a7-49d7-aca8-3d7ffb3fb256.png)

- 변경된 DB 내용

![image](https://user-images.githubusercontent.com/90389323/193715863-78908f87-be0a-470a-bcd1-b6ce9505d49a.png)
