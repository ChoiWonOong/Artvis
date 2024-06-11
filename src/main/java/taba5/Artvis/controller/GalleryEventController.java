package taba5.Artvis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taba5.Artvis.dto.special.GalleryEventDto;
import taba5.Artvis.service.special.GalleryEventService;

import java.util.List;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class GalleryEventController {
    private final GalleryEventService galleryEventService;
    // 행사 저장
    @PostMapping("/save")
    public void saveGalleryEvent(@RequestBody GalleryEventDto galleryEventDto){
        galleryEventService.save(galleryEventDto);
    }
    // 행사 리스트 출력
    @GetMapping("/list")
    public List<GalleryEventDto> getAllGalleryEvent(){
        return galleryEventService.findAll();
    }
    @GetMapping("/{id}")
    public GalleryEventDto getGalleryEvent(@PathVariable Long id){
        return galleryEventService.findById(id);
    }
}
