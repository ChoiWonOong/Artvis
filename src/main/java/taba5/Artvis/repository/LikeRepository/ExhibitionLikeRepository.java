package taba5.Artvis.repository.LikeRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.domain.Like.ExhibitionLike;
import taba5.Artvis.domain.Member;

import java.util.List;

public interface ExhibitionLikeRepository extends JpaRepository<ExhibitionLike, Exhibition>{
    List<ExhibitionLike> findByMember(Member member);
    Boolean existsByMember_IdAndExhibition_Id(Long memberId, Long exhibitionId);
}
