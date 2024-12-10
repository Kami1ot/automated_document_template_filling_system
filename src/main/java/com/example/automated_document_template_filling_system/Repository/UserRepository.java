package com.example.automated_document_template_filling_system.Repository;
import com.example.automated_document_template_filling_system.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
