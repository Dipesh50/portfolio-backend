package com.dipesh.portfolio.controller;

import com.dipesh.portfolio.model.Experience;
import com.dipesh.portfolio.model.Project;
import com.dipesh.portfolio.model.SkillCategory;
import com.dipesh.portfolio.repository.ExperienceRepository;
import com.dipesh.portfolio.repository.ProjectRepository;
import com.dipesh.portfolio.repository.SkillCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
@RequiredArgsConstructor
public class PortfolioController {

    private final ProjectRepository      projectRepository;
    private final ExperienceRepository   experienceRepository;
    private final SkillCategoryRepository skillCategoryRepository;

    @GetMapping("/projects")
    public ResponseEntity<List<Project>> getProjects() {
        return ResponseEntity.ok(projectRepository.findAllByOrderByDisplayOrderAsc());
    }

    @GetMapping("/experience")
    public ResponseEntity<List<Experience>> getExperience() {
        return ResponseEntity.ok(experienceRepository.findAllByOrderByDisplayOrderAsc());
    }

    @GetMapping("/skills")
    public ResponseEntity<List<SkillCategory>> getSkills() {
        return ResponseEntity.ok(skillCategoryRepository.findAllByOrderByDisplayOrderAsc());
    }
}
