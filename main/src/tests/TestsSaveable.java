package tests;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestsSaveable {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void testSave() throws ParseException, IOException {
        RunToDoList RunToDoList = new RunToDoList();
        List<Item> items = new ArrayList<>();
        ToDoList loi = new ToDoList(items,"");
        List<String> los = new ArrayList<>();

        Item i1 = new RegularItem("qwe", false, sdf.parse("1111-11-11"));
        Item i2 = new RegularItem("rty", false, sdf.parse("1211-11-11"));
        Item i3 = new RegularItem("yui", false, sdf.parse("1311-11-11"));
        Item i4 = new RegularItem("iop", false, sdf.parse("1411-11-11"));

        loi.setItems(i1);
        loi.setItems(i2);
        loi.setItems(i3);
        loi.setItems(i4);

        los.add(i1.itemToString(i1));
        los.add(i2.itemToString(i2));
        los.add(i3.itemToString(i3));
        los.add(i4.itemToString(i4));

        TestSave(RunToDoList, loi,"testlist.txt");

        List<String> lines = Files.readAllLines(Paths.get("testlist.txt"));

        assertEquals(los,lines);

    }

    public void TestSave(Saveable s, ToDoList loi, String filename) throws FileNotFoundException, UnsupportedEncodingException {
        s.save(loi, filename);
    }

}
