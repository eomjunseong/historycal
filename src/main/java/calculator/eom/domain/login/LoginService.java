package calculator.eom.domain.login;

import calculator.eom.domain.member.Member;
import calculator.eom.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    /**
     * @return null 로그인 실패
     */
    public Member login(String loginId, String password) {
        List<Member> byLongId = memberRepository.findByLoginId(loginId);

        return byLongId.stream().filter(m -> m.getPassword().equals(password)).findFirst().orElse(null);

    }
}
