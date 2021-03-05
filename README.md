#Spring_Boot_Practice

2021.03.04

1. '/hello' 접근 시 hello.html 페이지 출력 및 test 코드 작성

   - HelloController.getHello(): "/hello" get request 시에 작동하는 메서드
    
   - hello.html
   
   - gradle dependencies: thymeleaf, web

2. MySQL 연동하여 JPA로 데이터 접근
   
   - application.properties 수정
   
      - SpringBoot는 기본적으노 h2 database를 제공한다.
      
      - 다른 db를 사용할 경우, application.preperties에 connection 정보를 설정해야한다.
   
   - User: Entity (jpa에서 db 테이블과 대응하는 하나의 클래)
   
   - UserRepository: CrudRepository를 상속받아서 자동으로 CRUD 기능이 사용가능하다.

   - gradle dependencies: jpa, mysql-connector
    
3. '/blog' 접근 시 blog/index.html 페이지 출력

    - BlogController.getBlog(): "/blog" get request 시에 작동하는 메서
    
    - templates/blog/index.html: blog 페이지
    
    - static/assets/*: bootstrap assets
    
    - static/css/blog/blog.css
   
2021.03.05

1. sign-in 페이지

   - LoginController.getSignIn(): "/signin" get request 시에 sign-in 페이지 출력

   - templates/login/sign-in.html
   
   - sign 로직
     
      - blog sing up/in -> "/signin" get request
   
      - signin 페이지 signin 클릭 -> findByUserId()로 user 정보 조회 후 비교
    
2. sign-up 페이지

    - LoginController.getSignUp(), postSignUp()
    
    - LoginService, ServiceImpl. createUserInfo()
    
    - @Autowired를 통해서 DI를 해주지 않으면 Null Pointer Exception 발생한다.ㅁ
    
3. TODO
   
    - login 기능 관련하여 프로젝트 정리
      
        - UserController / LoginController
    
        - 접근 url 관련 정리 - root, blog, login 
    
    - login 기능 로직 세부 구현
    
        - 중복되는 ID 및 email 존재 여부 확인
    
        - sign up / sign in 실패 시 로직 (알림창)
    
        - login 이후 세션 관리