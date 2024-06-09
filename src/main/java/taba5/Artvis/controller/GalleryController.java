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
    // 미술관 저장
    @PostMapping("/save")
    public GalleryDto save(@RequestBody GalleryDto dto){
        return galleryService.save(dto);
    }
    // id로 미술관 불러오기
    @GetMapping("{id}")
    public GalleryDto getGallery(@PathVariable Long id){
        return galleryService.getGallery(id);
    }
    // 미술관 목록 불러오기
    @GetMapping("/list")
    public List<GalleryDto> getGalleryList(){
        return galleryService.getGalleryList();
    }
    // 미술관 검색
    @GetMapping("/search/{keyword}")
    public List<GalleryDto> searchGallery(@PathVariable String keyword){
        return galleryService.searchGallery(keyword);
    }
}
