package taba5.Artvis.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.dto.ReviewDto;

@Entity
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String contents;
    @Getter
    private byte rating;
    @Getter
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Getter
    @OneToOne
    @JoinColumn(name = "exhibition_id")
    private Exhibition exhibition;

    @Builder
    public Review(String title, String contents, byte rating, Member member, Exhibition exhibition) {
        this.title = title;
        this.contents = contents;
        this.rating = rating;
        this.member = member;
        this.exhibition = exhibition;
    }
    public ReviewDto toDto(){
        return ReviewDto.builder()
                .id(id)
                .memberId(member.getId())
                .exhibitionId(exhibition.getId())
                .title(title)
                .contents(contents)
                .rating(rating)
                .build();
    }
}
