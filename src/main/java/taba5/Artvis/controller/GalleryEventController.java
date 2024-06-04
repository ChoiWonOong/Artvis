package taba5.Artvis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taba5.Artvis.dto.special.GalleryEventDto;
import taba5.Artvis.service.special.GalleryEventService;

import java.util.List;

@RestController
@RequestMapping("/galleryEvent")
@RequiredArgsConstructor
public class GalleryEventController {
    private final GalleryEventService galleryEventService;

    @PostMapping("/save")
    public void saveGalleryEvent(@RequestBody GalleryEventDto galleryEventDto){
        galleryEventService.save(galleryEventDto);
    }
    @GetMapping("/list")
    public List<GalleryEventDto> getAllGalleryEvent(){
        return galleryEventService.findAll();
    }
}
