package taba5.Artvis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FlaskDto {
    String message;
    int result = 0; //default : pass
}