package com.dipesh.portfolio.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "skill_categories")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkillCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String category;

    @ElementCollection
    @CollectionTable(name = "skill_tags", joinColumns = @JoinColumn(name = "skill_category_id"))
    @Column(name = "tag")
    private List<String> tags;

    private int displayOrder;
}
