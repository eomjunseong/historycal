package calculator.eom.domain.member;

import calculator.eom.domain.history.History;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;

    @NotEmpty
    private String loginId; //로그인 ID
    @NotEmpty
    private String name; //사용자 이름
    @NotEmpty
    private String password;

    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY)
    private List<History> histories = new ArrayList<>();
}
