package com.fedesan14.repefit_api.data.repositories;

import com.fedesan14.repefit_api.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);
}
