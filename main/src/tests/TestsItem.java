package tests;

import model.Item;
import model.RegularItem;
import model.ToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestsItem {
    private Item item;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    public void setup() throws ParseException {
        item = new RegularItem("",false,sdf.parse("1111-11-11"));
    }

    @Test
    public void testSetName(){
        item.setName("swimming");
        assertEquals("swimming", item.getName());
    }

    @Test
    public void testDefaultStatus(){
        assertFalse(item.getStatus());
    }

    @Test
    public void testSetStatus(){
        item.setStatus(true);
        assertTrue(item.getStatus());
    }

    @Test
    public void testSetDueDate() throws ParseException {
        item.setDueDate(sdf.parse("2019-12-15"));
        assertEquals(0,
                item.getDueDate().compareTo(sdf.parse("2019-12-15")));
    }

    @Test
    public void testSetType(){
        assertEquals("Regular", item.getType());
        item.setType("Urgent");
        assertEquals("Urgent", item.getType());
    }

    @Test
    public void testSetToDoList(){
        List<Item> toDoItems = new ArrayList<>();
        ToDoList tdList = new ToDoList(toDoItems, "ToDoList");
        List<ToDoList> toDoLists = new ArrayList<>();
        toDoLists.add(tdList);

        item.setToDoList(tdList);
        assertEquals(toDoLists,item.getToDoList());
        assertEquals(1,item.getToDoList().size());

        item.setToDoList(tdList);
        assertEquals(1,item.getToDoList().size());

        }

    @Test
    public void testSetItem() throws ParseException {
        List<Item> toDoItems = new ArrayList<>();
        ToDoList tdList = new ToDoList(toDoItems, "ToDoList");
        List<ToDoList> toDoLists = new ArrayList<>();
        toDoLists.add(tdList);
        assertEquals("Regular", item.getType());
        assertEquals("", item.getName());
        assertFalse(item.getStatus());
        assertEquals(0,item.getDueDate().compareTo(sdf.parse("1111-11-11")));

        item.setItem(item, "qwe", true, sdf.parse("2019-12-15"), "Urgent",tdList);

        assertEquals("Urgent", item.getType());
        assertEquals("qwe", item.getName());
        assertTrue(item.getStatus());
        assertEquals(0,item.getDueDate().compareTo(sdf.parse("2019-12-15")));
        assertEquals(toDoLists,item.getToDoList());
    }

    @Test
    public void testIsPassDueDateAtDueDate() throws ParseException {
        item.setDueDate(sdf.parse("2019-12-15"));
        assertEquals(0, item.getDueDate().compareTo(sdf.parse("2019-12-15")));
    }

    @Test
    public void testIsPassDueDateBeforeDueDate() throws ParseException {
        item.setDueDate(sdf.parse("2019-12-15"));
        assertTrue(item.getDueDate().compareTo(sdf.parse("2020-12-15")) < 0);
    }

    @Test
    public void testIsPassDueDateAfterDueDate() throws ParseException {
        item.setDueDate(sdf.parse("2019-12-15"));
        assertTrue(item.getDueDate().compareTo(sdf.parse("2018-12-15")) > 0);
    }

    @Test
    public void testExtendDueDateSameMonth() throws ParseException {
        item.setDueDate(sdf.parse("2019-12-15"));
        item.extendDueDate(item,"10");
        assertEquals(0, item.getDueDate().compareTo(sdf.parse("2019-12-25")));
    }

    @Test
    public void testExtendDueDateDifferentYear() throws ParseException {
        item.setDueDate(sdf.parse("2019-12-15"));
        item.extendDueDate(item,"20");
        assertEquals(0, item.getDueDate().compareTo(sdf.parse("2020-01-04")));
    }

    @Test
    public void testExtendDueDateDifferentMonth() throws ParseException {
        item.setDueDate(sdf.parse("2019-7-15"));
        item.extendDueDate(item,"31");
        assertEquals(0, item.getDueDate().compareTo(sdf.parse("2019-08-15")));
    }

    @Test
    public void testShowDueDate() throws ParseException {
        item.setDueDate(sdf.parse("1998-12-15"));
        assertEquals("1998-12-15", item.showDueDate(item));
    }

    @Test
    public void testFinishedItem(){
        assertFalse(item.getStatus());
        item.finishedItem();
        assertTrue(item.getStatus());
    }

    @Test
    public void testItemToString() throws ParseException {
        Item regularItem = new RegularItem("", false, sdf.parse("1111-11-11"));
        assertEquals(" (Regular) 1111-11-11", regularItem.itemToString(regularItem));
    }




}
