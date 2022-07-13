package calculator.eom.domain.history;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRespository historyRespository;

    public History createHistory(History history){
        return historyRespository.save(history);
    }


}
