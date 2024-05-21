package taba5.Artvis.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import taba5.Artvis.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByUsername(String username);
    Optional<Member> findByUsername(String username);
    boolean existsByNickname(String nickname);
    Optional<Member> findByNickname(String nickname);
}
