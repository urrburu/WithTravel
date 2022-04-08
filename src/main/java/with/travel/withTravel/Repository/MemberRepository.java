package with.travel.withTravel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import with.travel.withTravel.Domain.Account;

@Transactional(readOnly = true)
public interface MemberRepository extends JpaRepository<Account, Long> {


    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);
}
