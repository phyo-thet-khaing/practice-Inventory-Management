package com.ptk.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "category_translation",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_category_translation_language",
            columnNames = {"category_id", "language_code"}
        )
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryTranslation {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "category_id",
        nullable = false
    )
    private Category category;
    
    @Column(length = 255)
    private String description;

    @Column(
        name = "language_code",
        nullable = false,
        length = 10
    )
    private String languageCode;

    @Column(
        nullable = false,
        length = 255
    )
    private String name;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

}
