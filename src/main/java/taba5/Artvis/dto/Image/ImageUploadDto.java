package taba5.Artvis.dto.Image;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class ImageUploadDto {
    private MultipartFile file;
}