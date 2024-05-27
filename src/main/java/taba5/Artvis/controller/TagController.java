package taba5.Artvis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taba5.Artvis.domain.Category;
import taba5.Artvis.domain.Exhibition.Tag;
import taba5.Artvis.dto.TagDto;
import taba5.Artvis.repository.CategoryRepository;
import taba5.Artvis.repository.TagRepository;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagController {
    private final TagRepository tagRepository;
    private final CategoryRepository categoryRepository;
    @PostMapping("/save")
    public String save(@RequestBody TagDto tagDto){
        Category category = categoryRepository.findById(tagDto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));
        Tag tag = new Tag();
        tag.setTagName(tagDto.getTagName());
        tag.setCategory(category);
        tagRepository.save(tag);
        return tag.getTagName();
    }
}
