import org.home.PhoneBook;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;

/**
 * Created by Halim on 08.02.2017.
 */
public class PhoneBookTest {
    private Map<String, ArrayList<String>> map;
    private PhoneBook phoneBook;
    //private String data = "Иванов И.К.";
    private String data = "Иванов И.И.";
    String result;

    @Before
    public void set() {
        phoneBook = new PhoneBook();
        map = new HashMap<String, ArrayList<String>>();
        map.put("Иванов И.И.", new ArrayList<String>(asList("+8 800 2000 500", "+8 800 200 600")));
        map.put("Петров П.П.", new ArrayList<String>(asList("+8 800 2000 700")));
        map.put("Сидоров С.С.", new ArrayList<String>(asList("+8 800 2000 800", "+8 800 2000 900", "+8 800 2000 000")));
        result = phoneBook.result(map, data);
    }

    @Test
    public void testResult() {
        Assert.assertEquals("Такого ФИО в БД нет", result);
    }
    @Test
    public void testResult2() {
        Assert.assertTrue("Правильно", result instanceof String);
    }
    @Test
    public void testResult3() {
        Assert.assertEquals("1. +8 800 2000 500\n" + "2. +8 800 200 600", result);
    }
}
