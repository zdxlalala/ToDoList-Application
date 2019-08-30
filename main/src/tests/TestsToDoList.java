package tests;

import model.Exceptions.SameThingException;
import model.Exceptions.TooManyThingsToDoException;
import model.Item;
import model.RegularItem;
import model.ToDoList;
import model.UrgentItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestsToDoList {
    private ToDoList toDoList;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private List<Item> items = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        toDoList = new ToDoList(items, "");
    }

    @Test
    public void testSetType(){
        assertEquals("",toDoList.getType());

        toDoList.setType("ToDoList");

        assertEquals("ToDoList", toDoList.getType());
    }

    @Test
    public void testSetItem() throws ParseException {
        List<Item> items = new ArrayList<>();
        List<ToDoList> tdList = new ArrayList<>();
        tdList.add(toDoList);
        Item item = new UrgentItem("", false, sdf.parse("1111-1111-11"));
        items.add(item);

        toDoList.setItems(item);

        assertEquals(items,toDoList.getItems());
        assertEquals(1,toDoList.getItems().size());

        toDoList.setItems(item);
        assertEquals(1,toDoList.getItems().size());
    }

    @Test
    public void testRemoveItem() throws ParseException {
        Item item = new UrgentItem("", false, sdf.parse("1111-1111-11"));
        toDoList.setItems(item);

        assertEquals(1,toDoList.getItems().size());

        toDoList.removeItem(0);

        assertEquals(0,toDoList.getItems().size());
    }

    @Test
    public void testAddItemToToDoList() throws ParseException {
        Item itemOne = new RegularItem("123",false,sdf.parse("1111-11-11"));
        Item itemTwo = new RegularItem("456",true,sdf.parse("1111-11-11"));
        Item itemThree = new RegularItem("789",true,sdf.parse("1111-11-11"));
        Item itemFour = new RegularItem("123",false,sdf.parse("1111-11-11"));
        Item itemFive = new RegularItem("111",true,sdf.parse("1111-11-11"));
        Item itemSix = new RegularItem("222",true,sdf.parse("1111-11-11"));


        //listSize == 0
        try {
            toDoList.addItemToToDoList(itemFour,toDoList);
        } catch (TooManyThingsToDoException tooManyThingsToDoException) {
            fail("Fail");
        } catch (SameThingException sameThingException) {
            fail("Fail");
        }

        //throw SamThingException
        try {
            toDoList.addItemToToDoList(itemOne,toDoList);
            fail("Fail");
        } catch (TooManyThingsToDoException tooManyThingsToDoException) {
            fail("Fail");
        } catch (SameThingException sameThingException) {

        }

        //add item
        try {
            toDoList.addItemToToDoList(itemTwo,toDoList);
        } catch (TooManyThingsToDoException tooManyThingsToDoException) {
            fail("Fail");
        } catch (SameThingException sameThingException) {
            fail("Fail");
        }


        //add item
        try {
            toDoList.addItemToToDoList(itemThree,toDoList);
        } catch (TooManyThingsToDoException tooManyThingsToDoException) {
            fail("Fail");
        } catch (SameThingException sameThingException) {
            fail("Fail");
        }

        //add item
        try {
            toDoList.addItemToToDoList(itemFive,toDoList);
        } catch (TooManyThingsToDoException tooManyThingsToDoException) {
            fail("Fail");
        } catch (SameThingException sameThingException) {
            fail("Fail");
        }

        //throw TooManyThingToDoException
        try {
            toDoList.addItemToToDoList(itemSix,toDoList);
            fail("Fail");
        } catch (TooManyThingsToDoException tooManyThingsToDoException) {


        } catch (SameThingException sameThingException) {
            fail("Fail");
        }

    }

    @Test
    public void testGetItemDescription() throws ParseException {
        Date dfDate = sdf.parse("1111-11-11");
        Item regularItem1 = new RegularItem("1",false, dfDate);
        Item regularItem2 = new RegularItem("2",false, dfDate);
        Item regularItem3 = new UrgentItem("3",false, dfDate);

        toDoList.setItems(regularItem1);
        toDoList.setItems(regularItem2);
        toDoList.setItems(regularItem3);

        toDoList.getItemsDescription();
    }

}
