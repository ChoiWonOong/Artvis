package taba5.Artvis.controller;

import org.springframework.web.bind.annotation.*;
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
}
