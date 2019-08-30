package tests;

import model.UrgentItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsUrgentItem {
    private UrgentItem item;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    public void setup() throws ParseException {
        item = new UrgentItem("",false,sdf.parse("1111-11-11"));
    }

    @Test
    public void testChangeType(){
        assertEquals("Urgent", item.getType());
        item.changeType(item);
        assertEquals("Regular", item.getType());
    }

    @Test
    public void testTypeString(){
        assertEquals("Urgent Item", item.typeString());
    }
}
