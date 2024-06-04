package taba5.Artvis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taba5.Artvis.dto.GalleryDto;
import taba5.Artvis.service.GalleryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gallery")
public class GalleryController {
    private final GalleryService galleryService;
    @PostMapping("/save")
    public GalleryDto save(GalleryDto dto){
        return galleryService.save(dto);
    }
    @GetMapping("{id}")
    public GalleryDto getGallery(@PathVariable Long id){
        return galleryService.getGallery(id);
    }
    @GetMapping("/list")
    public List<GalleryDto> getGalleryList(){
        return galleryService.getGalleryList();
    }
    @GetMapping("/search/{keyword}")
    public List<GalleryDto> searchGallery(@PathVariable String keyword){
        return galleryService.searchGallery(keyword);
    }
}
