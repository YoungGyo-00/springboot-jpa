# springboot-jpa
JPA 끄적끄적

---
1. 필요 개념
* getOne() vs findById()
  * getOne() : 대상 Entity에 대한 Lazy Load. 객체의 속성에 엑세스할 필요 없는 경우.
  * findById() : 주어진 ID에 대한 Entity 실제 로드. 모든 속성에 엑세스할 수 있도록 객체 로드.

2. 실행 오류
* ['dataSourceScriptDatabaseInitializer' defined in class path resource](https://www.inflearn.com/questions/224708)
 : application.yml DB 테이블 자동 생성 오류, data.sql -> import.sql 파일 이름 변경. spring 2.5.0 버전부터 사용법 바뀜