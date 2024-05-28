package taba5.Artvis.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.domain.Member;
import taba5.Artvis.domain.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>{
    List<Review> findAllByExhibition(Exhibition exhibition);
    List<Review> findAllByMember(Member member);
}
