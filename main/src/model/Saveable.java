package model;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface Saveable {
    public void save(ToDoList loi, String filename) throws FileNotFoundException, UnsupportedEncodingException;
}
