package taba5.Artvis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taba5.Artvis.dto.special.GalleryProgramDto;
import taba5.Artvis.service.special.GalleryProgramService;

import java.util.List;

@RestController
@RequestMapping("/galleryProgram")
@RequiredArgsConstructor
public class GalleryProgramController {
    private final GalleryProgramService galleryProgramService;

    @PostMapping("/save")
    public void saveGalleryProgram(@RequestBody GalleryProgramDto galleryProgramDto){
        galleryProgramService.save(galleryProgramDto);
    }
    @GetMapping("/list")
    public List<GalleryProgramDto> getAllGalleryEvent(){
        return galleryProgramService.findAll();
    }
}
