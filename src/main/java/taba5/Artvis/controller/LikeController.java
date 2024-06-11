package taba5.Artvis.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taba5.Artvis.dto.Exhibition.ExhibitionResponseDto;
import taba5.Artvis.dto.Like.InitializeLikeDto;
import taba5.Artvis.dto.Like.ArtworkLikeDto;
import taba5.Artvis.dto.Like.ExhibitionLikeDto;
import taba5.Artvis.service.LikeService;
import taba5.Artvis.util.SecurityUtil;

import java.util.List;

@RestController
@RequestMapping("/like")
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }
    // 전시 좋아요
    @PostMapping("/exhibition/{id}")
    public ExhibitionLikeDto saveExhibitionLike(@PathVariable Long id){
        return likeService.saveExhibitionLike(SecurityUtil.getCurrentMemberId(), id);
    }
    // 작품 좋아요
    @PostMapping("/artwork/{id}")
    public ArtworkLikeDto saveArtworkLike(@PathVariable Long id){
        return likeService.saveArtworkLike(SecurityUtil.getCurrentMemberId(), id);
    }
    // 전시회 초기 추천 설정
    @PostMapping("/initialize")
    public ResponseEntity<List<ExhibitionResponseDto>> initializeRecommend(@RequestBody InitializeLikeDto dto){
        return ResponseEntity.ok(likeService.initializeLike(dto, SecurityUtil.getCurrentMemberId()));
    }
}
