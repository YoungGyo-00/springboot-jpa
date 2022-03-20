# springboot-jpa
JPA 끄적끄적

---
## 필요 개념
### Entity
* DB에 저장하기 위해 유저가 정의한 클래스 -> Domain
* 일반적으로 RDBMS에서 Table을 객체화
* [Entity & Mapping 참고자료](https://gmlwjd9405.github.io/2019/08/11/entity-mapping.html)
  * `@Transient` : 필드에 매핑(x), 객체에 임시로 보관
  * `@Enumerated` : `EnumType.STRING`-> enum 이름을 데이터베이스에 저장
  * `@Column`
    * `name` : 필드에 매핑할 테이블 컬럼 이름
    * `nullable`
    * `unique` : `@Table`의 `uniqueConstraints`와 같지만, 한 컬럼에 대한 제약조건
  * `@GeneratedValue`
    * `IDENTITY` : 기본 키 생성을 데이터베이스에 위임(=`AUTO_INCREMENT`)
    * `SEQUENCE` : 데이터베이스 시퀀스를 사용해서 기본 키 할당
    * `TABLE` : 키 생성 테이블 사용
    * `AUTO` : 데이터베이스 방식에 따라 자동 선택(`Default`) -> ex) MySQL -> IDENTITY
---
### JPA CRUD
* JpaRepository interface 상속(Repository)
  * 기본적으로 CRUD가 가능하도록 제공.
  * Spring Data JPA에서ㅓ 제공하는 JpaRepository 인터페이스 상속 시, @Repository (x)
  ```
    public interface UserRepository extends JpaRepository<User, Long> {
    * Generic <T, ID(PK 값)>
        * T : Entity Type
        * ID : 식별자 Type(PK)
  }
  ```
* 주요 Method :
  * getOne() vs findById()
    * getOne() : 대상 Entity에 대한 Lazy Load. 객체의 속성에 엑세스할 필요 없는 경우.
    * findById() : 주어진 ID에 대한 Entity 실제 로드. 모든 속성에 엑세스할 수 있도록 객체 로드.
  * save(S) : 새로운 것 -> 저장, 이미 존재 -> 병합
  * delete(T) : 삭제
  * findAll : Sort or Pageable 조건을 파라미터로 제공할 수 있음.

* [쿼리 메소드 필터 조건 공식 문서](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation)
  * Spring Data JPA -> Method name을 분석하여, JPQL을 생성 후 실행 -> 코드 간결
  ![Query Method filter](./src/main/resources/static/QueryMethod.png)
  
---
### Embedded H2
* H2 Database : 자바 기반의 초 경량 RDBMS. 인 메모리 기반, JDBC API 지원, 브라우저에서 접속 가능한 콘솔 제공.
* `application.yml` 설정
  ```
    spring:
      h2:
        console:
          enabled: true  -> 콘솔 실행
      jpa:
        show-sql: true  -> 실행 sql문 로그
        properties:
          hibernate:
            format_sql: true -> 로그 띄우기
  ``` 
* `intellij database` 설정
  * server 실행 시 H2 URL 복사 -> 우측 상단 Database 탭 URL 복사
---
### Entity Listener
* Entity의 변화를 감지해 같은 테이블과 다른 테이블 모두 데이터를 조작
* [Hibernate docs - callback methods](https://docs.jboss.org/hibernate/stable/entitymanager/reference/en/html/listeners.html)
![Callback Methods](./src/main/resources/static/HibernateCallbacks.png)
* `listener Class`에는 의존성이 주입되지 않음 -> `BeanUtils` Component Class
  ```
    @Component
    public class BeanUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            BeanUtils.applicationContext = applicationContext;
        }

        public static <T> T getBean(Class<T> clazz){
            return applicationContext.getBean(clazz);
        }
    }

  ```
* BaseEntity Class
  * `@MappedSuperclass` : JPA Entity `createDate`, `modiifiedDate`를 컬럼으로
  * `@EntityListeners(AuditingEntityListener.class)` : Auditing 기능 사용
  * `@CreatedDate`, `@LastModifiedDate`
---
### Relation
* `@OneToOne`
  * 일대일 단방향 관계를 매핑, `@JoinColumn` 적용
  * 반대편에 `mappedBy` 적용 시, 양방향 관계 매핑 + 읽기 전용 필드
## 실행 오류
* ['dataSourceScriptDatabaseInitializer' defined in class path resource](https://www.inflearn.com/questions/224708)
 : application.yml DB 테이블 자동 생성 오류, data.sql -> import.sql 파일 이름 변경. spring 2.5.0 버전부터 사용법 바뀜