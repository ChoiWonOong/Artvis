package taba5.Artvis.domain.Review;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import taba5.Artvis.domain.BaseEntity;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.domain.Member;
import taba5.Artvis.dto.ReviewDto;

@Entity
@NoArgsConstructor
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
    @ManyToOne
    @JoinColumn(name = "exhibition_id")
    private Exhibition exhibition;

    @Getter
    private boolean isDummy = false;

    @Builder
    public Review(String contents, byte rating, Member member, Exhibition exhibition) {
        this.contents = contents;
        this.rating = rating;
        this.member = member;
        this.exhibition = exhibition;
    }
    public ReviewDto toDto(){
        return ReviewDto.builder()
                .id(id)
                .memberId(member.getId())
                .title(exhibition.getTitle())
                .exhibitionId(exhibition.getId())
                .contents(contents)
                .rating(rating)
                .build();
    }
    public void setDummy(){
        isDummy = true;
    }
}
