package taba5.Artvis.repository.LikeRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.domain.Like.ExhibitionLike;
import taba5.Artvis.domain.Member;

import java.util.List;
import java.util.Optional;

public interface ExhibitionLikeRepository extends JpaRepository<ExhibitionLike, Exhibition>{
    List<ExhibitionLike> findByMember(Member member);
    List<ExhibitionLike> findByMember_Id(Long memberId);
    Boolean existsByMember_IdAndExhibition_Id(Long memberId, Long exhibitionId);
    Optional<ExhibitionLike> findByMember_IdAndExhibition_Id(Long memberId, Long exhibitionId);
}
