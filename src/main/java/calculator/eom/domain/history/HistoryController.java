package calculator.eom.domain.history;

import calculator.eom.domain.member.Member;
import calculator.eom.domain.member.MemberRepository;
import calculator.eom.domain.member.MemberService;
import calculator.eom.web.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/history")
public class HistoryController {

    private final MemberRepository memberRepository;
    private final HistoryService historyService;
    private final MemberService memberService;
    private final EntityManager em;

    @GetMapping("/calculator_member")
    public String addForm(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model) {
        //세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            return "home";
        }
        //세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);
        System.out.println("-------------------------");
        System.out.println(loginMember.getName());
        return "calculator_member";
    }

    @PostMapping("/create")
    public String  addHistory(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model,@RequestBody HashMap<String, Object> map){
        System.out.println("2222222222222222222222222");
        String sendData = (String) map.get("sendData");
        System.out.println(sendData);

        //세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            return "home";
        }
        //세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);

        Member member = memberService.findById(loginMember.getId());

        History history = new History();
        history.setContent(sendData);

        History newHistory = historyService.createHistory(history);

        history.setMember(member);
        member.getHistories().add(newHistory);

        System.out.println("newHistory.getId() = " + newHistory.getId());
        System.out.println("newHistory.getId() = " + newHistory.getContent());
        System.out.println("newHistory.getId() = " + newHistory.getMember().getId());


//        //연관 관계 설정 --> 변경감지라고 생각
//        history1.setMember(member);


        return "calculator_member";
    }

}
