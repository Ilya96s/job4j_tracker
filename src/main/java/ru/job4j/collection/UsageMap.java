package ru.job4j.collection;

import java.util.HashMap;
import java.util.Map;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> account = new HashMap<>();
        account.put("iva.ivanov@mail.ru", "Ivanov Ivan Ivanovich");
        account.put("iva.ivanov@mail.ru", "Ivanov Ivan Ivanovich");
        account.put("ex.1@gmail.com", "Blinov Oleg Fedorovich");
        account.put("111threeunits@mail.ru", "Popov Nikita Sergeevich");
        account.put("111threeunits@mail.ru", "Popov Nikita Sergeevich");
        for (String s : account.keySet()) {
            String value = account.get(s);
            System.out.println(value);
        }
    }
}
