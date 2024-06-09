package taba5.Artvis.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.domain.Member;
import taba5.Artvis.domain.Review.Review;
import taba5.Artvis.domain.Review.ReviewId;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, ReviewId>{
    List<Review> findAllByExhibition(Exhibition exhibition);
    List<Review> findAllByMember(Member member);
    @Query("select count(r) from Review r where r.exhibition.id = :exhibitionId")
    int countByExhibition(Exhibition exhibition);
    @Query("select avg(r.rating) from Review r where r.exhibition.id = :exhibitionId")
    int avgRatingByExhibition(Exhibition exhibition);
}
