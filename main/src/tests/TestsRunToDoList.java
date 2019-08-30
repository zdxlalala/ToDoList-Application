package tests;

import model.*;
import model.Exceptions.SameThingException;
import model.Exceptions.TooManyThingsToDoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestsRunToDoList {
    private RunToDoList RunToDoList;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    public void setUp(){
        RunToDoList = new RunToDoList();
    }

    @Test
    public void testSplitOnSpaceNoSpace(){
        String string = "qwe1111";

        assertEquals("qwe1111", RunToDoList.splitOnSpace(string).get(0));

    }

    @Test
    public void testSplitOnSpace(){
        String string = "qwe 1111";

        assertEquals("qwe", RunToDoList.splitOnSpace(string).get(0));
        assertEquals("1111", RunToDoList.splitOnSpace(string).get(1));

    }

    @Test
    public void testLoadItem() throws ParseException {
        List<String> strings = new ArrayList<>();
        strings.add("");
        strings.add("(Urgent)");
        strings.add("1234-11-11");
        Item urgentItem = new UrgentItem("", false, sdf.parse("1111-1111-11"));
        List<Item> toDoItems = new ArrayList<>();
        ToDoList toDoList = new ToDoList(toDoItems, "ToDoList");

        assertEquals(0, toDoList.getItems().size());

        RunToDoList.loadItem(strings,toDoList,urgentItem);

        assertEquals(1,toDoList.getItems().size());
    }
}
