package ru.itvitality.sbrf.cu.l09.func;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * author: Dmitry Arkhangelskiy
 */
public class ComparatorExample {

    // Описание в отдельном классе
    static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.length() - o2.length();
        }
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("AA", "BBB", "C");

        // создание инстанса
        Collections.sort(list, new MyComparator());

        // анонимный класс
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        // Lambda
        Collections.sort(list, (String s1, String s2) -> {return s1.length() - s2.length();});

        // Типы можно вывести
        Collections.sort(list, (s1, s2) -> {return s1.length() - s2.length();});

        Collections.sort(list, Comparator.comparingInt(s -> s.length()));

        Collections.sort(list, Comparator.comparingInt(String::length));
    }
}
