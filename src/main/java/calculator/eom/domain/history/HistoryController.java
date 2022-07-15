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
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/history")
public class HistoryController {

    private final HistoryService historyService;
    private final MemberService memberService;

    @GetMapping("/list")
    public String items(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model) {
        //로그인 여부 체크
        //세션에 회원 데이터가 없으면 home
        System.out.println("-----$$$$$$$$$$$$$$$$$$$$$");
        if (loginMember == null) {
            return "home";
        }
        //세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);


        List<History> items = historyService.findByMember(loginMember);
        for (History item : items) {
            item.getContent();
        }
        model.addAttribute("items", items);
        return "historylist";
    }

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
    public String addHistory(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model, @RequestBody HashMap<String, Object> map) {
        String sendData = (String) map.get("sendData");
        System.out.println(sendData);

        //세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            return "home";
        }
        //세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);

        //member찾고
        Member member = memberService.findById(loginMember.getId()); // em

        History history = new History();
        history.setContent(sendData);

        member.addHistory(history);

        System.out.println("------------------------------");
        //insert 쿼리 바로 날라갈것으로 예상
        historyService.createHistory(history);
        System.out.println("------------------------------");

        return "calculator_member";
    }

    @GetMapping("/delete/{historyId}")
    public String deleteHistory(@PathVariable long historyId,
                                @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                                Model model) {

        //세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            return "home";
        }
        //세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);

        History byId = historyService.findById(historyId);
        historyService.deleteById(byId);

        return "redirect:/history/list";
    }
}