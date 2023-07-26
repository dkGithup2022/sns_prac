

### 기능적인 강점

- 컨트롤러에서 조회 가능한 Authentication 객체
- 권한에 계층 나누는게 생각보다 유용. 필터의 규칙과 컨테이너의 로직
- csrf, cors, session manage 등 기본적인 기능 

그리고 지속적인 업데이트


### 어노테이션 


#### 권한의 계층화

- preAuthorized
- permitAll

#### 테스트 어노테이션 

- withMockUser
- WithoutMockUser



#### 단점 .


  - 손이 많이가는 부분 있음 
    - controller advice 가 아닌 별도의 에러처리
    - 직관적이지 않은 사용법의 config 



  - 부족(?)한 자료
    - 딱 정석이외엔 용례나 래퍼런스 찾기 힘듬   (Authentication 객체에서 UsernamePasswordToken 이외의 용례를 찾기 힘듬.)


  - 직관적이지 않은 principal, authentication 등의 단어명
  - 


  - 애초에 권한 처리가 커스텀의 여지가 많다고 보임 
    - 찾기 힘든 자료들


