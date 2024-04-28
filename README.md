# 🌿 fine-dust: 미세먼지/초미세먼지 발령 시스템
## 💻 1. 프로젝트 소개
 - ### 프로젝트 소개
   - 2023년 3월 서울시의 미세먼지(PM-10)와 초미세먼지(PM-2.5) 데이터를 사용하여 경보 단계를 발령하는 시스템입니다.
   - 단계별 발령 기준
     |물질|경보 단계|발령 기준|
     |:---|:---|:---|
     |미세먼지(PM-10)|주의보|시간당 농도 150㎍/㎥ 이상 2시간 이상 지속 시|
     |미세먼지(PM-10)|경보|시간당 농도 300㎍/㎥ 이상 2시간 이상 지속 시|
     |초미세먼지 (PM-2.5)|주의보|시간당 농도 75㎍/㎥ 이상 2시간 이상 지속 시|
     |초미세먼지 (PM-2.5)|경보|시간당 농도 150㎍/㎥ 이상 2시간 이상 지속 시|
   - 경보 단계 등급표
     |등급|경보 단계|설명|
     |:---|:---|:---|
     |1|초미세먼지 경보|가장 심각한 상태, 건강에 매우 해로움|
     |2|미세먼지 경보|건강에 매우 해로울 수 있음|
     |3|초미세먼지 주의보|건강에 해로울 수 있음|
     |4|미세먼지 주의보|건강에 약간 해로울 수 있음|
  
 - ### 요구 사항
   - 경보 발령 기준이 충족되면 측정소(구별), 경보 단계, 발령 시간을 기록합니다. 
     - 이때 심각도에 따라 등급을 부여하며, 동일 시점에 경보가 복수 발생할 경우에 **가장 높은 순위의 등급** **(1)** > **(2)** > **(3)** > **(4)** 으로 발령합니다.
     - 이 경보 발령 정보를 데이터베이스에 저장합니다.
   - 측정된 데이터가 없는 경우 미세먼지, 초미세먼지 값은 0으로 처리합니다.
     - 이는 측정소 점검이 있던 날로 간주하고, 측정소 별 점검 내역을 데이터베이스에 저장합니다.
   - 별도의 화면(JavaScript, JSP)을 개발하지 않습니다.
  
- ### 개발 환경
  - 프로젝트에서 사용된 주요 기술과 도구는 다음과 같습니다.
  - java: Oracle OpenJDK 17.0.8: 프로젝트 사용 언어
  - Spring Boot 3.2.5: 웹 애플리케이션 서버 구축
  - MySQL 8.0.36: 서버 데이터베이스
  - Postman v10.24.24: API 개발 및 테스팅을 위한 툴
  - IntelliJ IDEA 2023.2 (Ultimate Edition): 프로젝트 개발 환경(IDE)

  </br></br>

## 📄 2. 시스템 구조
### - 패키지 구조

 <details>
  <summary>패키지 구조</summary>
  
  ``` 
📦src
 ┣ 📂main
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┗ 📂example
 ┃ ┃ ┃ ┃ ┗ 📂finedust
 ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MainController.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂data
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜JsonData.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AlertEntity.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AllWarningEntity.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CheckEntity.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FineDustEntity.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜WarningLevelEntity.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AlertRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AllWarningRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CheckRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FineDustRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜WarningLevelRepository.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AlertService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AllWarningService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CheckService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FineDustService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜JsonFileService.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜FineDustApplication.java
 ┃ ┗ 📂resources
 ┃ ┃ ┣ 📂static
 ┃ ┃ ┣ 📂templates
 ┃ ┃ ┣ 📜application.properties
 ┃ ┃ ┣ 📜data.json
 ┃ ┃ ┗ 📜data.sql
 ┗ 📂test
 ┃ ┗ 📂java
 ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┗ 📂example
 ┃ ┃ ┃ ┃ ┗ 📂finedust
 ┃ ┃ ┃ ┃ ┃ ┗ 📜FineDustApplicationTests.java
  ```

  </details>
  </br>

### - DB 설계
![fine-dust-db](https://github.com/hhhhh1hhhh/fine-dust-warning-system/assets/93113812/822a4298-4fa1-4dca-8cd1-44b84f6c0a06)

**<warninglevel_entity>**
  |칼럼명|데이터 타입|조건|설명|
  |:---|:---|:---|:---|
  |id|int|PK, Not Null, Auto Increment|기본 키|
  |level|int|Not Null, Unique|등급|
  |type|varchar|Not Null|경보 단계|
  |Description|varchar|Not Null|설명|
 - (초)미세먼지 경보 등급에 대한 테이블로 애플리케이션 시작 시, 각 경보의 등급과 설명이 포함됩니다.
</br>

**<finedust_entity>**
  |칼럼명|데이터 타입|조건|설명|
  |:---|:---|:---|:---|
  |id|bigint|PK, Not Null, Auto Increment|기본 키|
  |dateTime|datetime|Not Null|발령 시간|
  |pm10|int|Not Null|미세먼지 농도||
  |pm25|int|Not Null|초미세먼지 농도|
  |stationCode|varchar|Not Null|측정소 코드|
  |sataionName|varchar|Not Null|측정소(구별)|
 - 미세먼지 관련 정보가 저장되는 테이블로, JSON 데이터를 파싱하여 각 관측소에서 측정된 미세먼지 농도 데이터를 저장합니다.
</br>

**<check_entity>**
  |칼럼명|데이터 타입|조건|설명|
  |:---|:---|:---|:---|
  |id|bigint|PK, Not Null, Auto Increment|기본 키|
  |dateTime|datetime|Not Null|발령 시간|
  |pm10|int|Not Null|미세먼지 농도||
  |pm25|int|Not Null|초미세먼지 농도|
  |sataionName|varchar|Not Null|측정소(구별)|
  - finedust_entity에서 측정소 점검이 있던 날을(pm10 = 0, pm25 = 0) 필터링하여 측정소 별 점검내역을 저장하는 테이블입니다.
</br>

**<allwarning_entity>**
  |칼럼명|데이터 타입|조건|설명|
  |:---|:---|:---|:---|
  |id|bigint|PK, Not Null, Auto Increment|기본 키|
  |dateTime|datetime||발령 시간|
  |pmValue|int||(초)미세먼지 농도|
  |sataionName|varchar||측정소(구별)|
  |warninglevel_id|int|FK|경보 단계 번호|
  - 전체 경보 및 주의보 정보가 기록되는 테이블로, finedust_entity에서 발령 기준에 따라 필터링한 데이터를 저장합니다.
</br>

**<alert_entity>**
  |칼럼명|데이터 타입|조건|설명|
  |:---|:---|:---|:---|
  |id|bigint|PK, Not Null, Auto Increment|기본 키|
  |dateTime|datetime|Not Null|발령 시간|
  |sataionName|varchar|Not Null|측정소(구별)|
  |warninType|int|FK|경보 단계|
  - allwarning_entity에서 동일 시점에 경보가 복수 발생했을 때, 가장 높은 순위의 등급 정보를 저장하는 테이블입니다.
</br>
</br>

### - API 설계
- postman을 통해 API docs로 만들었습니다.
- https://documenter.getpostman.com/view/34553670/2sA3BuVUCD

