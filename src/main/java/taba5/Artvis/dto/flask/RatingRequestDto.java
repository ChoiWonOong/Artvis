package taba5.Artvis.dto.flask;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class RatingRequestDto extends FlaskRequestDto{
    private Long userId;
}