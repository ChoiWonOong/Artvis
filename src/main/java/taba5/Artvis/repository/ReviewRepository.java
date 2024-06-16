package taba5.Artvis.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.domain.Member;
import taba5.Artvis.domain.Review.Review;
import taba5.Artvis.domain.Review.ReviewId;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, ReviewId>{
    List<Review> findAllByExhibitionIdAndIsDummy(Long exhibitionId, boolean isDummy);

    List<Review> findByOrderByIdDesc();
    List<Review> findAllByMemberAndIsDummy(Member member, boolean isDummy);
    @Query("select count(r) from Review r where r.member = :member")
    int countByMember(Member member);
    @Query("select count(r) from Review r where r.exhibitionId = :exhibitionId")
    int countByExhibition(Exhibition exhibition);
    @Query("select avg(r.rating) from Review r where r.exhibitionId = :exhibitionId")
    int avgRatingByExhibition(Exhibition exhibition);

    List<Review> findByContentsContainingAndIsDummy(String keyword, boolean isDummy);
    @Query("select r from Review r order by r.rating desc")
    List<Review> findAllOrderByRatingDescAndIsDummy(boolean isDummy);
    @Query("select r from Review r order by r.rating asc")
    List<Review> findAllOrderByRatingAscAndIsDummy(boolean isDummy);

}
