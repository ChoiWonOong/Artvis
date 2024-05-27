package taba5.Artvis.repository.LikeRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import taba5.Artvis.domain.Like.ArtworkLike;

import java.util.List;

public interface ArtworkLikeRepository extends JpaRepository<ArtworkLike, Long>{
    List<ArtworkLike> findByMember_Id(Long memberId);
}
