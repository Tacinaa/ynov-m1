package org.example.todolistback.repository;

import org.example.todolistback.model.Todo;
import org.example.todolistback.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUser(User user);
    List<Todo> findByUserNot(User user);
}