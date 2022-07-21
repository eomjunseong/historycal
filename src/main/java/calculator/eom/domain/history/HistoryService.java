package calculator.eom.domain.history;

import calculator.eom.domain.member.Member;
import calculator.eom.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRespository historyRespository;
    private final MemberRepository memberRepository;
    public History createHistory(Long memberId,String sendData){

        Member member = memberRepository.findById(memberId).get();

        History history= new History();
        history.setContent(sendData);
        history.setMember(member);

        return historyRespository.save(history);
    }

    public List<History> findByMember(Member member){
        return historyRespository.findByMember(member);
    }

    public History findById(long historyId){
        return historyRespository.findById(historyId).orElse(null);
    }

    public void deleteById(History history){
        historyRespository.delete(history);
    }


}
