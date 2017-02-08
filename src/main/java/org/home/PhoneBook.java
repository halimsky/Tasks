package org.home;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import static java.util.Arrays.*;

/**
 * Created by Halim on 08.02.2017.
 */
public class PhoneBook {
    public static void main(String[] args) throws IOException {
        Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        map.put("Иванов И.И.", new ArrayList<String>(asList("+8 800 2000 500", "+8 800 200 600")));
        map.put("Петров П.П.", new ArrayList<String>(asList("+8 800 2000 700")));
        map.put("Сидоров С.С.", new ArrayList<String>(asList("+8 800 2000 800", "+8 800 2000 900", "+8 800 2000 000")));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите ФИО");
        String data = br.readLine();
        System.out.println(new PhoneBook().result(map, data));

    }

    public String result(Map<String, ArrayList<String>> map, String data) {
        String successResult = "";
        String fail = "Такого ФИО в БД нет";
        if (map.containsKey(data)) {
            ArrayList<String> numbers = map.get(data);
            int count = 0;
            for (String number : numbers) {
                successResult += ++count + ". " + number + "\n";
            }
            successResult = successResult.trim();
            return successResult;
        }
        return fail;
    }
}
