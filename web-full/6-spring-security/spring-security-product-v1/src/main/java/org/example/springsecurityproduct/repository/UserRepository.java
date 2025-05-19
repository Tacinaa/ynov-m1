package org.example.springsecurityproduct.repository;

import org.example.springsecurityproduct.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
