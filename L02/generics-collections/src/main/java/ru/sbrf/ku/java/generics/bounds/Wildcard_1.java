package ru.sbrf.ku.java.generics.bounds;

import ru.sbrf.ku.java.generics.bounds.entries.Animal;
import ru.sbrf.ku.java.generics.bounds.entries.Cat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sergey
 * created on 23.11.18.
 */
public class Wildcard_1 {

    public static void main(String[] args) {

        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Animal());

        print(animalList);
        printWild(animalList);

        List<Cat> catList = new ArrayList<>();
        catList.add(new Cat());

        //print(catList); //Ошибка
        printWild(catList);

    }

    private static void print(List<Animal> animalList) {
        animalList.forEach(System.out::println);
    }

    private static void printWild(List<? extends Animal> animalList) {
        animalList.forEach(System.out::println);
    }

}
