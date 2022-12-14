# 사람들이 생각하는 mbti 별 특징

링크 : https://mbtitestbypeople.link/mbtiTest

제작 기간 : 2022.11.28 ~ 2022.12.29

제작 인원 : 1명(개인 토이 프로젝트)

사용 기술 : Spring boot, Java 8.0, JPA, REST API, OAuth2.0, Spring Security, AWS Cloud, Linux Server, JavaScript, Thymeleaf, MySQL8.0

핵심 기능 : JPA를 활용한 페이징, OAuth2.0과 Spring Security를 이용한 소셜 로그인

느낀점 : 
+ 프론트/백엔드
  + 기존에 사용해본 적이 없던 Spring boot, JPA, REST API를 프로젝트를 위해 공부했고, 적용하려고 노력했다. 그 과정에서 REST 방식의 설계를 준수하려고 노력했으나 resource에 따른 분류에 있어서 다소 어려움이 있었다. 
  + 테스트 주도의 개발 방식을 적용하려고 했으나, 익숙하지 않은 부분이 있었다.

+ 데이터베이스
  + JPA를 처음 활용해보면서 생각보다 많은 부분이 쿼리 작성없이 해결될 수 있는 것에 놀랐다. 특히 게시판 페이징 기능도 자체 구현된 인터페이스로 간단하게 처리할 수 있던 것이 기억에 남는다.

+ 운영서버
  + 기존에 실무에서는 AWS로 이미 구축되어 있는 서버를 관리해본 경험만 있었다. 이번 토이 프로젝트를 통해서 AWS 서버의 실제 배포까지 경험하면서 웹개발의 전 사이클을 경험해볼 수 있었다. 
  + AWS의 로드 밸런서를 이용해 https 보안을 적용하는 부분에 어려움이 있었다. 로드 밸런서 및 대상 그룹에서 http와 https 간 redirect 설정에 문제가 있었고, 이를 적절하게 수정해 해결할 수 있었다.


