package taba5.Artvis.service.special;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import taba5.Artvis.Exception.ErrorCode;
import taba5.Artvis.Exception.RestApiException;
import taba5.Artvis.domain.Gallery;
import taba5.Artvis.domain.Special.GalleryProgram;
import taba5.Artvis.dto.special.GalleryProgramDto;
import taba5.Artvis.repository.Special.GalleryProgramRepository;
import taba5.Artvis.repository.GalleryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GalleryProgramService {
    private final GalleryProgramRepository galleryProgramRepository;
    private final GalleryRepository galleryRepository;

    public void save(GalleryProgramDto dto){
        Gallery gallery = galleryRepository.findById(dto.getGalleryId()).orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        GalleryProgram event = dto.toEntity();
        event.setGallery(gallery);
        galleryProgramRepository.save(dto.toEntity());
    }
    public List<GalleryProgramDto> findAll(){
        return galleryProgramRepository.findAll().stream()
                .map(GalleryProgram::toDto)
                .toList();
    }

    public GalleryProgramDto findById(Long id) {
        return galleryProgramRepository.findById(id)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR)).toDto();
    }

    public List<GalleryProgram> searchProgram(String keyword) {
        return galleryProgramRepository.findByTitleContaining(keyword);
    }
}