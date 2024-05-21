package taba5.Artvis.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
@Getter
public class FileDto {
    private MultipartFile file;
    private String originalFileName;
    private int fileAttached;


}
