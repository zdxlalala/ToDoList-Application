package tests;

import model.RegularItem;
import model.ToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsRegularItem {
    private RegularItem item;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    public void setup() throws ParseException {
        item = new RegularItem("",false,sdf.parse("1111-11-11"));
    }

    @Test
    public void testChangeType(){
        assertEquals("Regular", item.getType());
        item.changeType(item);
        assertEquals("Urgent", item.getType());
    }

    @Test
    public void testTypeString(){
        assertEquals("Regular Item", item.typeString());
    }
}
