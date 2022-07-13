package calculator.eom.domain.history;

import calculator.eom.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRespository extends JpaRepository<History,Long> {

    List<History> findByMember(Member member);

}

