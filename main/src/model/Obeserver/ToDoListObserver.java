package model.Obeserver;

import model.ToDoList;

public interface ToDoListObserver {
    void update(ToDoList toDoList);
}
