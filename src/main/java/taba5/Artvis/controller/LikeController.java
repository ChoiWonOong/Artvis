package taba5.Artvis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping("/save")
    public ExhibitionLikeDto save(ExhibitionLikeDto exhibitionLikeDto){
        return likeService.save(exhibitionLikeDto);
    }
    @GetMapping("/get/exhibition")
    public List<ExhibitionLikeDto> getExhibitionLikeList(Long memberId){
        return likeService.getExhibitionLikes(memberId);
    }
}
