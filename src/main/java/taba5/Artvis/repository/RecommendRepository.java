package taba5.Artvis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taba5.Artvis.domain.Member;
import taba5.Artvis.domain.Recommend.Recommend;
import taba5.Artvis.domain.Recommend.RecommendId;

import java.util.List;

public interface RecommendRepository extends JpaRepository<Recommend, RecommendId>{
    List<Recommend> findAllByMemberId(Member memberId);
}
