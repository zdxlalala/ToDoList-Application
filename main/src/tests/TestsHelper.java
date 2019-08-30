package tests;

import model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestsHelper {
    private Helper helper;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    public void setup() {
        helper = new Helper();
    }

    @Test
    public void testEqual(){
        assertTrue(helper.equal(1,1));
        assertFalse(helper.equal(1,0));
    }

    @Test
    public void testMinusOne(){
        assertEquals(1,helper.minusOne(2));
        assertEquals(3,helper.minusOne(4));
    }

}
