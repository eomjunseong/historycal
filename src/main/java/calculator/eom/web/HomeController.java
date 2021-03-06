package calculator.eom.web;

import calculator.eom.domain.member.Member;
import calculator.eom.domain.member.MemberRepository;
import calculator.eom.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
//    private final SessionManager sessionManager;

    @GetMapping("/")
    public String homeLogin(
            @Login Member loginMember,
            Model model) {

        //세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            return "home";
        }

        //세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);
        return "loginHome";
    }
    @GetMapping("/calculator_guest")
    public String defaultCalculator(){
        return "calculator_guest";
    }

}