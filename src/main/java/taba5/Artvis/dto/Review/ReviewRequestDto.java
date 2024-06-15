package taba5.Artvis.dto.Review;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewRequestDto {
    private Long id;
    private Long memberId;
    private Long exhibitionId;
    private String title;
    private String contents;
    private byte rating;

    @Builder
    public ReviewRequestDto(Long id, Long memberId, Long exhibitionId, String title, String contents, byte rating) {
        this.id = id;
        this.memberId = memberId;
        this.exhibitionId = exhibitionId;
        this.title = title;
        this.contents = contents;
        this.rating = rating;
    }
}
