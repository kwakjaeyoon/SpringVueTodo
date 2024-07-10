package spring.todo.main.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import spring.todo.main.domain.Todo;

import java.util.List;

@Mapper
public interface TodoMapper {

    void insertTodo(Todo todo);

    List<Todo> getAllTodos();

    List<Todo> getTodoByStatus(boolean status);

    Todo findByItem(String item);

    void updateTodoStatus( Long id, boolean status);

    void deleteTodoById(Long id);
}
