package taba5.Artvis.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taba5.Artvis.Exception.ErrorResponse;
import taba5.Artvis.Exception.RestApiException;
import taba5.Artvis.domain.Special.GalleryEvent;
import taba5.Artvis.domain.Special.GalleryProgram;
import taba5.Artvis.dto.Exhibition.ExhibitionResponseDto;
import taba5.Artvis.dto.special.GalleryEventDto;
import taba5.Artvis.dto.special.GalleryProgramDto;
import taba5.Artvis.service.ExhibitionService;
import taba5.Artvis.service.ReviewService;
import taba5.Artvis.service.special.GalleryEventService;
import taba5.Artvis.service.special.GalleryProgramService;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {
    private final ExhibitionService exhibitionService;
    private final GalleryEventService galleryEventService;
    private final GalleryProgramService galleryProgramService;
    private final ReviewService reviewService;
    // 전시회 검색
    @PostMapping("exhibition")
    public ResponseEntity<?> searchExhibition(@RequestBody Map<String,String> keywordDto){
        try {
            log.info("keyword: {}", keywordDto.get("keyword"));
            return ResponseEntity.ok(exhibitionService.searchExhibition(keywordDto.get("keyword")));
        }catch (RestApiException e){
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }catch (RuntimeException e){
            return ErrorResponse.toResponseEntity(e, "BAD_REQUEST");
        }
    }
    // 미술관 검색
    @PostMapping("program")
    public ResponseEntity<?> searchProgram(@RequestBody Map<String,String> keywordDto){
        try{
            return ResponseEntity.ok(galleryProgramService.searchProgram(keywordDto.get("keyword"))
                    .stream().map(GalleryProgram::toDto).toList());
        }catch (RestApiException e){
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }catch (RuntimeException e){
            return ErrorResponse.toResponseEntity(e, "BAD_REQUEST");
        }
    }
    @PostMapping("event")
    public ResponseEntity<?> searchEvent(@RequestBody Map<String,String> keywordDto){
        try{
            return ResponseEntity.ok(galleryEventService.searchEvent(keywordDto.get("keyword"))
                    .stream().map(GalleryEvent::toDto).toList());
        }catch (RestApiException e){
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }catch (RuntimeException e){
            return ErrorResponse.toResponseEntity(e, "BAD_REQUEST");
        }
    }
    @PostMapping("review")
    public ResponseEntity<?> searchReview(@RequestBody Map<String,String> keywordDto) {
        try {
            return ResponseEntity.ok(reviewService.searchReview(keywordDto.get("keyword")));
        } catch (RestApiException e) {
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        } catch (RuntimeException e) {
            return ErrorResponse.toResponseEntity(e, "BAD_REQUEST");
        }
    }
}
