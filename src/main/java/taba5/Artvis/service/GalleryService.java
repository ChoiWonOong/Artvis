package taba5.Artvis.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import taba5.Artvis.Exception.ErrorCode;
import taba5.Artvis.Exception.RestApiException;
import taba5.Artvis.domain.Gallery;
import taba5.Artvis.dto.GalleryDto;
import taba5.Artvis.repository.GalleryRepository;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class GalleryService {
    private final GalleryRepository galleryRepository;

    public GalleryDto save(GalleryDto dto){
        Gallery gallery = dto.toEntity();
        log.info("gallery name: {}", gallery.getName());
        galleryRepository.save(gallery);
        return dto;
    }
    public GalleryDto getGallery(Long id){
        return galleryRepository.findById(id).map(Gallery::toDto)
                .orElseThrow(()-> new RestApiException(ErrorCode.NOT_EXIST_ERROR));
    }
    public List<GalleryDto> getGalleryList(){
        return galleryRepository.findAll().stream()
                .map(Gallery::toDto)
                .toList();
    }

    public List<GalleryDto> searchGallery(String keyword) {
        return galleryRepository.findByNameContaining(keyword).stream()
                .map(Gallery::toDto)
                .toList();
    }
}
