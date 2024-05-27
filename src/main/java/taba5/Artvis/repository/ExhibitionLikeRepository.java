package taba5.Artvis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.domain.Like.ExhibitionLike;

import java.util.List;

public interface ExhibitionLikeRepository extends JpaRepository<ExhibitionLike, Long> {
    List<ExhibitionLike> findByMember(Long memberId);
}
