package taba5.Artvis.service.special;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import taba5.Artvis.Exception.ErrorCode;
import taba5.Artvis.Exception.RestApiException;
import taba5.Artvis.domain.Gallery;
import taba5.Artvis.domain.Special.GalleryEvent;
import taba5.Artvis.dto.special.GalleryEventDto;
import taba5.Artvis.repository.GalleryRepository;
import taba5.Artvis.repository.Special.GalleryEventRepository;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GalleryEventService {
    private final GalleryEventRepository galleryEventRepository;
    private final GalleryRepository galleryRepository;

    public void save(GalleryEventDto dto){
        Gallery gallery = galleryRepository.findById(dto.getGalleryId()).orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        GalleryEvent event = dto.toEntity();
        event.setGallery(gallery);
        galleryEventRepository.save(dto.toEntity());
    }
    public List<GalleryEventDto> findAll(){
        return galleryEventRepository.findAll().stream()
                .map(GalleryEvent::toDto)
                .toList();
    }

    public GalleryEventDto findById(Long id) {
        return galleryEventRepository.findById(id)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR)).toDto();
    }

    public List<GalleryEvent> searchEvent(String keyword) {
        return galleryEventRepository.findByTitleContaining(keyword);
    }

    public GalleryEventDto setUrl(Long id, Map<String, String> dto) {
        GalleryEvent event = galleryEventRepository.findById(id)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        event.setImageUrl(dto.get("url"));
        return galleryEventRepository.save(event).toDto();
    }
}
