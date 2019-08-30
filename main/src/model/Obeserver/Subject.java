package model.Obeserver;

import model.ToDoList;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<ToDoListObserver> observers= new ArrayList<>();

    public void addObserver(ToDoListObserver observer){
        if(!(observers.contains(observer)))
            observers.add(observer);
    }

    public void notifyObserver(ToDoList toDoList){
        for(ToDoListObserver observer: observers){
            observer.update(toDoList);
        }
    }
}
