package repository;

import vo.Todo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TodoRepositoryImpl implements TodoRepository{
    //  저장소 생성 코드
    private final Map<String, List<Todo>> todoMap = new HashMap<>();

    @Override
    public void add(String date, Todo todo) {
        if (!todoMap.containsKey(date)) {
            todoMap.put(date, new ArrayList<>());
        }
        todoMap.get(date).add(todo);
    }

    @Override
    public List<Todo> findByDate(String date) {
        if (!todoMap.containsKey(date)) {
            return null;
        }
        return todoMap.get(date);
    }

    @Override
    public Map<String, List<Todo>> findAll() {
        return todoMap;
    }

    @Override
    public void update(String date, int index, Todo todo) {
        List<Todo> list = todoMap.get(date);
        if (index < 0 || index >= list.size()) {
            return;
        }
        list.set(index, todo);
        todoMap.put(date, list);
    }

    @Override
    public void delete(String date, int index) {
        List<Todo> list = todoMap.get(date);
        if (index < 0 || index >= list.size()) {
            return;
        }
        list.remove(index);
    }

    @Override
    public void complete(String date, int index) {
        List<Todo> list = todoMap.get(date);
        if (index < 0 || index >= list.size()) {
            return;
        }
        list.get(index).setCompleted(true);
    }
}
