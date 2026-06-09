package com.dipesh.portfolio.repository;

import com.dipesh.portfolio.model.SkillCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SkillCategoryRepository extends JpaRepository<SkillCategory, Long> {
    List<SkillCategory> findAllByOrderByDisplayOrderAsc();
}
