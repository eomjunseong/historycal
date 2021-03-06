package calculator.eom.domain.history;

import calculator.eom.domain.member.Member;
import calculator.eom.domain.member.MemberRepository;
import calculator.eom.domain.member.MemberService;
import calculator.eom.web.SessionConst;
import calculator.eom.web.argumentresolver.Login;
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
    public String items(@Login Member loginMember, Model model) {

        //로그인 여부 체크
        //세션에 회원 데이터가 없으면 home
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
    public String addForm(@Login Member loginMember, Model model) {
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
    public String addHistory(@Login Member loginMember,
                             Model model,
                             @RequestBody HashMap<String, Object> map) {

        String sendData = (String) map.get("sendData");

        //세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            return "home";
        }
        //세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);
        historyService.createHistory(loginMember.getId(),sendData);

        return "calculator_member";
    }

    @GetMapping("/delete/{historyId}")
    public String deleteHistory(@PathVariable long historyId,
                                @Login Member loginMember,
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