package org.example.todolistback.controller;

import org.example.todolistback.model.Todo;
import org.example.todolistback.model.User;
import org.example.todolistback.repository.TodoRepository;
import org.example.todolistback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    // ✅ Tous les utilisateurs peuvent voir les todos (ADMIN : les siens, USER : ceux des autres)
    @GetMapping("")
    public List<Todo> getTodos(Authentication auth) {
        User currentUser = userRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        boolean isAdmin = currentUser.getRoles().stream()
                .anyMatch(role -> role.getName().equals("ROLE_ADMIN"));

        if (isAdmin) {
            return todoRepository.findByUser(currentUser); // Les siens
        } else {
            return todoRepository.findByUserNot(currentUser); // Ceux des autres
        }
    }

    // ✅ Création réservée à l'ADMIN
    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public Todo createTodo(@RequestBody Todo todo, Authentication auth) {
        User currentUser = userRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        todo.setUser(currentUser);
        return todoRepository.save(todo);
    }

    // ✅ Mise à jour réservée à l'ADMIN
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Todo updateTodo(@PathVariable Long id, @RequestBody Todo updatedTodo) {
        Todo existing = todoRepository.findById(id).orElseThrow();
        existing.setTitle(updatedTodo.getTitle());
        existing.setDescription(updatedTodo.getDescription());
        existing.setCompleted(updatedTodo.isCompleted());
        return todoRepository.save(existing);
    }

    // ✅ Suppression réservée à l'ADMIN
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTodo(@PathVariable Long id) {
        todoRepository.deleteById(id);
    }
}