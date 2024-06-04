package taba5.Artvis.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.domain.Member;
import taba5.Artvis.domain.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>{
    List<Review> findAllByExhibition(Exhibition exhibition);
    List<Review> findAllByMember(Member member);
    @Query("select count(r) from Review r where r.exhibition.id = :exhibitionId")
    int countByExhibition(Exhibition exhibition);
    @Query("select avg(r.rating) from Review r where r.exhibition.id = :exhibitionId")
    int avgRatingByExhibition(Exhibition exhibition);
}
