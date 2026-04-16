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
        //  맵에 키가 없다면 빈 리스트 생성
        if (!todoMap.containsKey(date)) {
            todoMap.put(date, new ArrayList<>());
        }
        //  가져온 todo를 리스트에 추가하기
        todoMap.get(date).add(todo);
    }

    @Override
    public List<Todo> findByDate(String date) {
        if (!todoMap.containsKey(date)) {
            //  맵을 키로 열어봤더니 리스트가 없는 경우
            //  빈 리스트 생성 후 리턴
            return new ArrayList<>();
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
        if(list == null){
            return;
        }
        if (index < 0 || index >= list.size()) {
            return;
        }
        list.set(index, todo);
    }

    @Override
    public void delete(String date, int index) {
        //  날짜에 해당하는 할 일 리스트를 먼저 얻어온다.
        List<Todo> list = todoMap.get(date);
        //  해당 날짜에 리스트가 비어 있는지 확인하고 비어 있으면 리턴
        if(list == null){
            return;
        }
        //  삭제할 index가 범위 내에서 있는지 확인
        if (index < 0 || index >= list.size()) {
            return;
        }
        // 리스트에서 받은 index번호를 삭제한다.
        list.remove(index);
        //  삭제 후에 리스트가 비어 있으면, 사물함도 뺀다.
        if(list.isEmpty()){
            todoMap.remove(date);
        }
    }

    @Override
    public void complete(String date, int index) {
        List<Todo> list = todoMap.get(date);
        if(list == null){
            return;
        }
        if (index < 0 || index >= list.size()) {
            return;
        }
        Todo todo = list.get(index);
        todo.setCompleted(!todo.isCompleted());
    }
}
