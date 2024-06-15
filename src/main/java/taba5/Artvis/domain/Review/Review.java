package taba5.Artvis.domain.Review;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import taba5.Artvis.domain.BaseEntity;
import taba5.Artvis.domain.Member;

@Entity
@NoArgsConstructor
@Getter
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"member_id", "exhibition_id"})

})
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    private String contents;
    @Getter
    private byte rating;
    
    @Getter
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Getter
    @Column(name = "exhibition_id")
    private Long exhibitionId;

    @Getter
    private boolean isDummy = false;

    @Builder
    public Review(String contents, byte rating, Member member, Long exhibitionId) {
        this.contents = contents;
        this.rating = rating;
        this.member = member;
        this.exhibitionId = exhibitionId;
    }
    public void setDummy(){
        isDummy = true;
    }
}
