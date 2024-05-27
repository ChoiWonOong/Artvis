package taba5.Artvis.domain.Exhibition;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import taba5.Artvis.domain.Category;

@Entity
@NoArgsConstructor
@Setter
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Getter
    private String tagName;
}
