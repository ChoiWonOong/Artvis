package taba5.Artvis.repository.LikeRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import taba5.Artvis.domain.Like.ExhibitionLike;
import taba5.Artvis.domain.Member;

import java.util.List;

public interface ExhibitionLikeRepository extends JpaRepository<ExhibitionLike, Long>{
    List<ExhibitionLike> findByMember(Member member);
}
