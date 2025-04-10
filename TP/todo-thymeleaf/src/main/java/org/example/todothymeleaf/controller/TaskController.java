package org.example.todothymeleaf.controller;

import org.example.todothymeleaf.model.Task;
import org.example.todothymeleaf.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        model.addAttribute("newTask", new Task());
        return "task/list";
    }

    @PostMapping("/add")
    public String addTask(@ModelAttribute Task newTask) {
        taskRepository.save(newTask);
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return "redirect:/tasks";
    }

    @GetMapping("/toggle/{id}")
    public String toggleStatus(@PathVariable Long id) {
        taskRepository.findById(id).ifPresent(task -> {
            task.setStatus(!task.isStatus());
            taskRepository.save(task);
        });
        return "redirect:/tasks";
    }
}