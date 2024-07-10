package spring.todo.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.todo.main.Service.TodoService;
import spring.todo.main.domain.Todo;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getTodos(@RequestParam(required = false) Boolean status){
        if(status == null)
            return todoService.getAllTodos();
        else if (status == false)
            return todoService.getTodoByStatus(false);
        else if (status == true)
            return todoService.getTodoByStatus(true);
        else
            return todoService.getAllTodos();
    }

    @PostMapping
    public ResponseEntity<?> saveTodo(@RequestBody Todo todo){
        try{
            Todo newTodo = todoService.saveTodo(todo);
            return ResponseEntity.ok(newTodo);
        }catch (RuntimeException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public void UpdateTodo(@PathVariable Long id){
        todoService.updateTodo(id, true);
    }

    @DeleteMapping("/{id}")
    public void DeleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
    }
}
