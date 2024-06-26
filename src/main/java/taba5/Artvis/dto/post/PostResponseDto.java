package taba5.Artvis.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class PostResponseDto {
    private Long post_id;
    private String title;
    private String contents;
    private String nickname;
    private String post_type;
}
