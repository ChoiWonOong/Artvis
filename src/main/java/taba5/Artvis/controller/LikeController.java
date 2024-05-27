package taba5.Artvis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taba5.Artvis.dto.Like.ArtworkLikeDto;
import taba5.Artvis.dto.Like.ExhibitionLikeDto;
import taba5.Artvis.service.LikeService;

import java.util.List;

@RestController
@RequestMapping("/like")
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/exhibition")
    public ExhibitionLikeDto saveExhibitionLike(ExhibitionLikeDto exhibitionLikeDto){
        return likeService.saveExhibitionLike(exhibitionLikeDto);
    }
    @PostMapping("/artwork")
    public ArtworkLikeDto saveArtworkLike(ArtworkLikeDto exhibitionLikeDto){
        return likeService.saveArtworkLike(exhibitionLikeDto);
    }
    @GetMapping("/get/exhibition")
    public List<ExhibitionLikeDto> getExhibitionLikeList(Long memberId){
        return likeService.getExhibitionLikes(memberId);
    }
}
