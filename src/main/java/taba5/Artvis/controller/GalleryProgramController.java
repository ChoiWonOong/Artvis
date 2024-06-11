package taba5.Artvis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taba5.Artvis.dto.special.GalleryProgramDto;
import taba5.Artvis.service.special.GalleryProgramService;

import java.util.List;

@RestController
@RequestMapping("/program")
@RequiredArgsConstructor
public class GalleryProgramController {
    private final GalleryProgramService galleryProgramService;
    // 프로그램 저장
    @PostMapping("/save")
    public void saveGalleryProgram(@RequestBody GalleryProgramDto galleryProgramDto){
        galleryProgramService.save(galleryProgramDto);
    }
    // 프로그램 리스트 출력
    @GetMapping("/list")
    public List<GalleryProgramDto> getAllGalleryEvent(){
        return galleryProgramService.findAll();
    }
    @GetMapping("/{id}")
    public GalleryProgramDto getGalleryEvent(@PathVariable Long id){
        return galleryProgramService.findById(id);
    }
}
