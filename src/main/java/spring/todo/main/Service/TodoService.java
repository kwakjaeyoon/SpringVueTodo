package spring.todo.main.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.todo.main.domain.Todo;
import spring.todo.main.mapper.TodoMapper;

import java.util.List;

@Service
public class TodoService {

    private TodoMapper todoMapper;

    @Autowired
    public TodoService(TodoMapper todoMapper) {
        this.todoMapper = todoMapper;
    }

    public Todo saveTodo(Todo todo){
        Todo existTodo = todoMapper.findByItem(todo.getItem());
        if(existTodo !=null){
            throw new RuntimeException("Item already exists");
        }
        todoMapper.insertTodo(todo);
        return todo;
    }

    public List<Todo> getAllTodos(){
        return todoMapper.getAllTodos();
    }

    public List<Todo> getTodoByStatus(boolean status){
        return todoMapper.getTodoByStatus(status);
    }

    public void updateTodo(Long id, boolean status){
        todoMapper.updateTodoStatus(id, status);
    }

    public void deleteTodo(Long id){
        todoMapper.deleteTodoById(id);
    }


}
