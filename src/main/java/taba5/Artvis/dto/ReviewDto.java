package taba5.Artvis.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewDto {
    private Long id;
    private Long memberId;
    private Long exhibitionId;
    private String title;
    private String content;
    private byte rating;

    @Builder
    public ReviewDto(Long id, Long memberId, Long exhibitionId, String title, String content, byte rating) {
        this.id = id;
        this.memberId = memberId;
        this.exhibitionId = exhibitionId;
        this.title = title;
        this.content = content;
        this.rating = rating;
    }
}
