package com.community.numble.system.email.repository;

import com.community.numble.system.email.domain.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

    Email findByToEmailAndTokenVerified(String toEmail, String verified);

    boolean existsByToEmailAndTokenVerified(String toEmail, String verified);

}
