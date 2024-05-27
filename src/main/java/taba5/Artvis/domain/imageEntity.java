package taba5.Artvis.domain;

import jakarta.persistence.MappedSuperclass;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@MappedSuperclass
public abstract class imageEntity {
    private List<MultipartFile> files;
}
