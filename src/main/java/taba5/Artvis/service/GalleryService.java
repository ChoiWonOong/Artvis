package taba5.Artvis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import taba5.Artvis.Exception.ErrorCode;
import taba5.Artvis.Exception.RestApiException;
import taba5.Artvis.domain.Gallery;
import taba5.Artvis.dto.GalleryDto;
import taba5.Artvis.repository.GalleryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GalleryService {
    private final GalleryRepository galleryRepository;

    public GalleryDto save(GalleryDto dto){
        galleryRepository.save(dto.toEntity());
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
}
