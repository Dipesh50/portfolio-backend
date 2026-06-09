package com.dipesh.portfolio.config;

import com.dipesh.portfolio.model.*;
import com.dipesh.portfolio.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final ProjectRepository      projectRepository;
    private final ExperienceRepository   experienceRepository;
    private final SkillCategoryRepository skillCategoryRepository;

    @Override
    public void run(String... args) {
        seedProjects();
        seedExperience();
        seedSkills();
    }

    private void seedProjects() {
        if (projectRepository.count() > 0) return;

        projectRepository.saveAll(List.of(
            Project.builder()
                .name("StockSense")
                .status(Project.Status.IN_DEVELOPMENT)
                .category("Spring Boot & React")
                .description("AI-Powered Stock Market Analysis Platform combining real-time market data feed, " +
                             "intelligent stock analysis, and news sentiment scoring. Architected for secure " +
                             "watchlist management and real-time updates.")
                .techTags(List.of("Java","Spring Boot","Spring AI","MySQL","React.js","JWT Authentication","REST APIs"))
                .githubUrl("https://github.com/Dipesh50")
                .liveUrl(null)
                .displayOrder(1)
                .build(),

            Project.builder()
                .name("EduAI")
                .status(Project.Status.PRODUCTION)
                .category("AI-Powered Education")
                .description("Intelligent learning platform designed to provide personalized study plans, " +
                             "adaptive quizzes, and AI-generated learning content based on user objectives " +
                             "and academic progress.")
                .techTags(List.of("Java","Spring Boot","Spring AI","MySQL","REST APIs"))
                .githubUrl("https://github.com/Dipesh50")
                .liveUrl("https://eduai-frontend-flax.vercel.app")
                .displayOrder(2)
                .build(),

            Project.builder()
                .name("Mastermind AI")
                .status(Project.Status.DEVELOPER_TOOL)
                .category("Compiler & Code Analysis")
                .description("Backend compiler analysis tool analyzing Git repositories for logical bugs and " +
                             "translating compiler errors into structured explanations and recommendations.")
                .techTags(List.of("Java","Spring AI","Prompt Engineering","REST APIs"))
                .githubUrl("https://github.com/Dipesh50")
                .liveUrl(null)
                .displayOrder(3)
                .build()
        ));
    }

    private void seedExperience() {
        if (experienceRepository.count() > 0) return;

        experienceRepository.saveAll(List.of(
            Experience.builder()
                .title("Software Development Intern")
                .company("ZagFox Technologies Private Limited")
                .startDate("March 2026")
                .endDate("May 2026")
                .description("Focused on Java backend development, Spring Boot microservices, Hibernate ORM, " +
                             "and database indexing. Implemented Prompt Engineering modules using Spring AI " +
                             "for runtime error parsing and code recommendation APIs.")
                .bullets(List.of(
                    "Developed clean backend features using Java and Spring Boot.",
                    "Created and optimized RESTful APIs for database management.",
                    "Applied Hibernate ORM and designed MySQL tables for efficiency.",
                    "Collaborated in git-based workflows with peer developers."
                ))
                .displayOrder(1)
                .build(),

            Experience.builder()
                .title("Technical Team Member")
                .company("NEXUS MUJ")
                .startDate("January 2025")
                .endDate("May 2026")
                .description("Contributed to internal software development and tech workshops within the " +
                             "university's official community. Collaborated in setting up servers, maintaining " +
                             "application code, and conducting technical reviews.")
                .bullets(List.of(
                    "Assisted in backend framework configuration and codebase setups.",
                    "Participated in peer code reviews and architectural discussions.",
                    "Contributed to deployment operations using Git and GitHub."
                ))
                .displayOrder(2)
                .build()
        ));
    }

    private void seedSkills() {
        if (skillCategoryRepository.count() > 0) return;

        skillCategoryRepository.saveAll(List.of(
            SkillCategory.builder()
                .category("Programming Languages")
                .tags(List.of("Java","Python","JavaScript","SQL"))
                .displayOrder(1).build(),

            SkillCategory.builder()
                .category("Backend & Frameworks")
                .tags(List.of("Spring Boot","Spring Data JPA","Hibernate","Spring AI","RESTful APIs","MVC Architecture"))
                .displayOrder(2).build(),

            SkillCategory.builder()
                .category("Frontend Development")
                .tags(List.of("HTML5 & CSS3","JavaScript (ES6+)","React.js"))
                .displayOrder(3).build(),

            SkillCategory.builder()
                .category("Databases & Tools")
                .tags(List.of("MySQL","PostgreSQL","Git & GitHub","Postman","IntelliJ IDEA","VS Code"))
                .displayOrder(4).build()
        ));
    }
}
