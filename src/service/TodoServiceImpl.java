package service;

import repository.TodoRepository;
import vo.Todo;

import java.util.List;
import java.util.Map;

public class TodoServiceImpl implements TodoService{
    private final TodoRepository todoRepository;
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public void addTodo(String date, String time, String task) {
        Todo todo = new Todo(time, task, false);
        todoRepository.add(date, todo);
    }

    @Override
    public List<Todo> getTodosByDate(String date) {
        return todoRepository.findByDate(date);
    }

    @Override
    public Map<String, List<Todo>> getAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public void updateTodo(String date, int index, String time, String task) {
        Todo todo = new Todo(time, task, false);
        todoRepository.update(date, index, todo);
    }

    @Override
    public void deleteTodo(String date, int index) {
        todoRepository.delete(date, index);
    }

    @Override
    public void completeTodo(String date, int index) {
        todoRepository.complete(date, index);
    }
}
