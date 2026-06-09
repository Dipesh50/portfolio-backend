package com.dipesh.portfolio.controller;

import com.dipesh.portfolio.dto.ContactRequest;
import com.dipesh.portfolio.model.ContactMessage;
import com.dipesh.portfolio.repository.ContactMessageRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
@Slf4j
public class ContactController {

    private final ContactMessageRepository contactMessageRepository;
    private final JavaMailSender           mailSender;

    @Value("${portfolio.contact.notify-email}")
    private String notifyEmail;

    @PostMapping
    public ResponseEntity<Map<String, String>> submit(@Valid @RequestBody ContactRequest req) {

        // 1. Persist to DB
        ContactMessage saved = contactMessageRepository.save(
            ContactMessage.builder()
                .name(req.getName())
                .email(req.getEmail())
                .message(req.getMessage())
                .receivedAt(LocalDateTime.now())
                .build()
        );
        log.info("Saved contact message id={} from={}", saved.getId(), req.getEmail());

        // 2. Send notification email
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(notifyEmail);
            mail.setSubject("📬 New Portfolio Contact: " + req.getName());
            mail.setText(String.format(
                "You received a new portfolio message:\n\n" +
                "Name:    %s\n" +
                "Email:   %s\n" +
                "Message:\n%s\n\n" +
                "Received at: %s",
                req.getName(), req.getEmail(), req.getMessage(), saved.getReceivedAt()
            ));
            mailSender.send(mail);
            log.info("Notification email sent for contact id={}", saved.getId());
        } catch (Exception e) {
            // Don't fail the response if email send fails — message is already saved
            log.warn("Failed to send notification email: {}", e.getMessage());
        }

        return ResponseEntity.ok(Map.of("message", "Message received. I'll get back to you shortly!"));
    }
}
