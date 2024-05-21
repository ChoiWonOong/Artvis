package taba5.Artvis.domain;

import jakarta.persistence.*;

@Entity
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    @Column
    private String originalFileName;
    @Column
    private String storedFileName;
}
