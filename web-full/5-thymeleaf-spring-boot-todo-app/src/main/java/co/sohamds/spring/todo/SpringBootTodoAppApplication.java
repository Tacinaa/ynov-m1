package co.sohamds.spring.todo;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import co.sohamds.spring.todo.domain.Todo;
import co.sohamds.spring.todo.repository.TodoRepository;

@SpringBootApplication
public class SpringBootTodoAppApplication implements CommandLineRunner
{
@Autowired
public TodoRepository todoRepository;
public static void main(String[] args) {
SpringApplication.run(SpringBootTodoAppApplication.class, args);
}

@Override
public void run(String... args) throws Exception {
	
	Todo test=Todo.builder().id(10).completed("its completed").todoItem("python ML").build();
	System.out.println(test.toString());
	List<Todo> todos = Arrays.asList(new Todo("Suivre le top cours" , "Yes"), new Todo("Aller manger", "No"), new Todo("Faire du sport", "No"), new Todo("Rester concentrer", "Yes"));
todos.forEach(todoRepository::save);

}
}
