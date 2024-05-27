package taba5.Artvis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taba5.Artvis.dto.Exhibition.ExhibitionRequestDto;
import taba5.Artvis.dto.Exhibition.ExhibitionResponseDto;
import taba5.Artvis.service.ExhibitionService;
import taba5.Artvis.util.SecurityUtil;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exhibition")
public class ExhibitionController {
    private final ExhibitionService exhibitionService;
    @PostMapping("/create")
    public ResponseEntity<ExhibitionResponseDto> createExhibition(@RequestBody ExhibitionRequestDto exhibitionRequestDto){
        return ResponseEntity.ok(exhibitionService.createExhibition(exhibitionRequestDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ExhibitionResponseDto> getExhibition(@PathVariable Long id){
        return ResponseEntity.ok(exhibitionService.getExhibition(id));
    }
    @GetMapping("/liked")
    public ResponseEntity<List<ExhibitionResponseDto>> likeExhibition(){
        return ResponseEntity.ok(exhibitionService.getLikedExhibition(SecurityUtil.getCurrentMemberId()));
    }
}
