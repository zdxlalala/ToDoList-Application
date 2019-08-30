package tests;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsLoadable {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private RunToDoList RunToDoList = new RunToDoList();

    @Test
    public void testLoad() throws IOException, ParseException {
        List<Item> items1 = new ArrayList<>();
        List<Item> items2 = new ArrayList<>();
        ToDoList loi1 = new ToDoList(items1,"");
        ToDoList loi2 = new ToDoList(items2,"");


        Item i1 = new RegularItem("qweqwrwq", false, sdf.parse("1111-11-11"));
        Item i2 = new RegularItem("qwewqrwqadasd", false, sdf.parse("1111-11-11"));
        Item i3 = new RegularItem("qweqweqwasd", false, sdf.parse("1234-12-23"));
        Item i4 = new RegularItem("qsadasf", false, sdf.parse("2234-11-20"));

        loi2.setItems(i1);
        loi2.setItems(i2);
        loi2.setItems(i3);
        loi2.setItems(i4);


        List<String> lines = Files.readAllLines(Paths.get("testload.txt"));


        TestLoad(RunToDoList,lines, loi1);



        assertEquals(loi2.getItems().get(0).getName(),loi1.getItems().get(0).getName());
        assertEquals(loi2.getItems().get(1).getName(),loi1.getItems().get(1).getName());
        assertEquals(loi2.getItems().get(2).getName(),loi1.getItems().get(2).getName());
        assertEquals(loi2.getItems().get(3).getName(),loi1.getItems().get(3).getName());

        assertEquals(loi2.getItems().get(0).getStatus(),loi1.getItems().get(0).getStatus());
        assertEquals(loi2.getItems().get(1).getStatus(),loi1.getItems().get(1).getStatus());
        assertEquals(loi2.getItems().get(2).getStatus(),loi1.getItems().get(2).getStatus());
        assertEquals(loi2.getItems().get(3).getStatus(),loi1.getItems().get(3).getStatus());

        assertEquals(loi2.getItems().get(0).getDueDate(),loi1.getItems().get(0).getDueDate());
        assertEquals(loi2.getItems().get(1).getDueDate(),loi1.getItems().get(1).getDueDate());
        assertEquals(loi2.getItems().get(2).getDueDate(),loi1.getItems().get(2).getDueDate());
        assertEquals(loi2.getItems().get(3).getDueDate(),loi1.getItems().get(3).getDueDate());

    }

    public void TestLoad(Loadable l, List<String> lines, ToDoList loi) throws ParseException {
        l.load(lines, loi);
    }
}
