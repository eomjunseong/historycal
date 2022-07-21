# historycal

*datajpa
*session(login)
*interceptor(login,log)
*custom annotation @Login
*h2 db
*bootstrap
*thymleaf 


calculator.eom 안에서 

domain 과 
web 으로 분리


domain : history, login, member에 관한 entity, service, repostiroy, controller 가 존재

web : argumentresolver에 @Login 어노테이션 관련 

web : interceptor에 로그인 관련 인터셉터 설정

web : 이외에, HomeController, SessionConst, WebConfig 를 둠


