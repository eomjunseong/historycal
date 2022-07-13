package calculator.eom.domain.history;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRespository extends JpaRepository<History,Long> {
}
