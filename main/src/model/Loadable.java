package model;

import java.text.ParseException;
import java.util.List;

public interface Loadable {
    public void load(List<String> lines, ToDoList loi)throws ParseException;
}
